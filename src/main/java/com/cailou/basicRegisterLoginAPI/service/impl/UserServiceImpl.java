package com.cailou.basicRegisterLoginAPI.service.impl;

import com.cailou.basicRegisterLoginAPI.repository.RoleRepository;
import com.cailou.basicRegisterLoginAPI.repository.UserRepository;
import com.cailou.basicRegisterLoginAPI.dto.user.CheckPasswordRequestDTO;
import com.cailou.basicRegisterLoginAPI.dto.user.PasswordRequestDTO;
import com.cailou.basicRegisterLoginAPI.dto.user.UpdateUserRequestDTO;
import com.cailou.basicRegisterLoginAPI.dto.user.UserRequestDTO;
import com.cailou.basicRegisterLoginAPI.model.RoleModel;
import com.cailou.basicRegisterLoginAPI.model.UserModel;
import com.cailou.basicRegisterLoginAPI.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }


    @Override
    public UserModel saveUser(UserRequestDTO userRequest) {
        Optional<UserModel> userAlreadyExists = this.userRepository.findByLogin(userRequest.getLogin());
        if (userAlreadyExists.isPresent()) {
            throw new RuntimeException("Login já existe, tente outro");
        }

        Optional<UserModel> userEmailAlreadyExists = this.userRepository.findByEmail(userRequest.getEmail());
        if (userEmailAlreadyExists.isPresent()) {
            throw new RuntimeException("Email de usuário já existe, tente outro");
        }

        Optional<UserModel> userCellPhoneAlreadyExists = this.userRepository.findByCellphone(userRequest.getCellphone());
        if (userCellPhoneAlreadyExists.isPresent()) {
            throw new RuntimeException("Número de celular já existe, tente outro");
        }

        UserModel userModel = new UserModel();
        userModel.setPassword(this.passwordEncoder.encode(userRequest.getPassword()));
        userModel.setLogin(userRequest.getLogin());
        userModel.setEmail(userRequest.getEmail());
        userModel.setName(userRequest.getName());
        userModel.setCellphone(userRequest.getCellphone());
        userModel.setActive(userRequest.isActive());


        Optional<RoleModel> role = roleRepository.findById(UUID.fromString(userRequest.getRoleId()));
        if (role.isEmpty()) {
            throw new RuntimeException("Role não encontrada!");
        }
        userModel.setRole(role.get());


        return this.userRepository.save(userModel);
    }


    @Transactional
    @Override
    public UserModel updatePassword(String userId, PasswordRequestDTO passwordRequest) {
        var user = listUser(userId);

        if (!passwordRequest.getConfirmPassword().equals(passwordRequest.getNewPassword())){
            throw new RuntimeException("Senha invalida, a nova senha e a confirmação não são iguais");
        }
        if (this.passwordEncoder.matches(passwordRequest.getNewPassword(), user.getPassword())) {
            throw new RuntimeException("Senha invalida, nova senha não pode ser igual a antiga");
        }

        user.setPassword(this.passwordEncoder.encode(passwordRequest.getNewPassword()));
        return this.userRepository.save(user);
    }

    public boolean checkPassword(String userId, CheckPasswordRequestDTO passwordRequest) {
        var user = listUser(userId);

        if (!this.passwordEncoder.matches(passwordRequest.getOldPassword(), user.getPassword())){
            throw new RuntimeException("Senhas não são iguais");
        }

        return true;
    }

    @Transactional
    @Override
    public UserModel updateUser(String userId, UpdateUserRequestDTO userRequest) {
        var userModel = listUser(userId);

        userModel.setEmail(userRequest.getEmail());
        userModel.setName(userRequest.getName());
        userModel.setCellphone(userRequest.getCellphone());

        Optional<RoleModel> role = roleRepository.findById(UUID.fromString(userRequest.getRoleId()));
        if (role.isEmpty()) {
            throw new RuntimeException("Role não encontrada!");
        }
        userModel.setRole(role.get());
        return this.userRepository.save(userModel);
    }

    @Override
    public UserModel listUser(String userId) {
        Optional<UserModel> user = this.userRepository.findById(UUID.fromString(userId));
        if (user.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado!");
        }
        return user.get();
    }

    @Override
    public UserModel switchUserActivity(String userId) {
        var userAlreadyExists = this.userRepository.findById(UUID.fromString(userId));

        if (userAlreadyExists.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado!");
        }

        var user = userAlreadyExists.get();
        user.setActive(!user.isActive());
        this.userRepository.save(user);
        return user;
    }

    @Override
    public List<RoleModel> listAllRoles() {
        return this.roleRepository.findAll();
    }
}
