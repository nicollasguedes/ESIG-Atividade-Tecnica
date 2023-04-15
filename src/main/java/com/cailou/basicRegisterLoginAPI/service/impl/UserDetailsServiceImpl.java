package com.cailou.basicRegisterLoginAPI.service.impl;

import com.cailou.basicRegisterLoginAPI.repository.UserRepository;
import com.cailou.basicRegisterLoginAPI.detail.UserDetailData;
import com.cailou.basicRegisterLoginAPI.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service()
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<UserModel> user = this.userRepository.findByLogin(login);

        if(user.isEmpty()){
            throw new RuntimeException(("Login ou Senha inválidos!"));
        }

        return UserDetailData.creat(user.get());
    }

    public UserDetails loadUserById(UUID id) throws UsernameNotFoundException {
        Optional<UserModel> user = this.userRepository.findById(id);
        if(user.isEmpty()) {
            throw new RuntimeException("Email ou Senha invalidos!");
        }

        return UserDetailData.creat(user.get());
    }

    public UserDetails loadUserByName(String name) throws UsernameNotFoundException {
        Optional<UserModel> user = this.userRepository.findByLogin(name);
        if(user.isEmpty()) {
            throw new RuntimeException("Email ou Senha invalidos!");
        }

        return UserDetailData.creat(user.get());
    }
}
