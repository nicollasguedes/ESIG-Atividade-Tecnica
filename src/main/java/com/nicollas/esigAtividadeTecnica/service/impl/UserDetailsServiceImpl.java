package com.nicollas.esigAtividadeTecnica.service.impl;

import com.nicollas.esigAtividadeTecnica.detail.UserDetailData;
import com.nicollas.esigAtividadeTecnica.model.UserModel;
import com.nicollas.esigAtividadeTecnica.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;

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

    public UserDetails loadUserById(BigInteger id) throws UsernameNotFoundException {
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
