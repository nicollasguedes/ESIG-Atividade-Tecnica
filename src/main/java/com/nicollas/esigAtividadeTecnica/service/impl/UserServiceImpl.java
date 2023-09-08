package com.nicollas.esigAtividadeTecnica.service.impl;


import com.nicollas.esigAtividadeTecnica.dto.user.CheckPasswordRequestDTO;
import com.nicollas.esigAtividadeTecnica.dto.user.PasswordRequestDTO;
import com.nicollas.esigAtividadeTecnica.dto.user.UserRequestDTO;
import com.nicollas.esigAtividadeTecnica.model.UserModel;
import com.nicollas.esigAtividadeTecnica.repository.UserRepository;
import com.nicollas.esigAtividadeTecnica.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PessoaServiceImpl pessoaService;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, PessoaServiceImpl pessoaService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.pessoaService = pessoaService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public UserModel saveUser(UserRequestDTO userRequest) throws ParseException {
        Optional<UserModel> userAlreadyExists = this.userRepository.findByLogin(userRequest.getLogin());
        if (userAlreadyExists.isPresent()) {
            throw new RuntimeException("Login já existe, tente outro");
        }

        UserModel userModel = new UserModel();
        userModel.setPassword(this.passwordEncoder.encode(userRequest.getPassword()));
        userModel.setLogin(userRequest.getLogin());
        userModel.setActive(userRequest.isActive());

        userModel = this.userRepository.save(userModel);

        userModel.setPessoa(pessoaService
                .savePessoaByUserModel(userModel ,userRequest.getPessoaRequestDTO()));

        return this.userRepository.save(userModel);
    }


    @Transactional
    @Override
    public UserModel updateUserPassword(BigInteger userId, PasswordRequestDTO passwordRequest) {
        var user = listUser(userId);

        if (passwordRequest.getConfirmPassword().equals(passwordRequest.getNewPassword())) {
            throw new RuntimeException("Senha invalida, a nova senha e a confirmação não podem ser iguais");
        }
        if (this.passwordEncoder.matches(passwordRequest.getNewPassword(), user.getPassword())) {
            throw new RuntimeException("Senha invalida, nova senha não pode ser igual a antiga");
        }

        user.setPassword(this.passwordEncoder.encode(passwordRequest.getNewPassword()));
        return this.userRepository.save(user);
    }

    @Override
    @Transactional
    public Boolean deleteUser(BigInteger userId) {
        var user = listUser(userId);
        if (pessoaService.deletePessoaByPessoaId(user.getPessoa().getId())) {
            return userRepository.deleteByIdAndReturnBool(user.getId());
        } else return false;
    }

    public boolean checkPassword(BigInteger userId, CheckPasswordRequestDTO passwordRequest) {
        var user = listUser(userId);

        if (!this.passwordEncoder.matches(passwordRequest.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Senhas não são iguais");
        }

        return true;
    }

    @Override
    public UserModel listUser(BigInteger userId) {
        Optional<UserModel> user = this.userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado!");
        }
        return user.get();
    }

    @Override
    public UserModel listUser(String login) {
        Optional<UserModel> user = this.userRepository.findByLogin(login);
        if (user.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado!");
        }
        return user.get();
    }

    @Transactional
    @Override
    public UserModel switchUserActive(BigInteger userId) {
        UserModel user = listUser(userId);

        user.setActive(!user.isActive());
        this.userRepository.save(user);
        return user;
    }
}
