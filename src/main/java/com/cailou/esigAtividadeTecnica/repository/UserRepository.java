package com.cailou.esigAtividadeTecnica.repository;

import com.cailou.esigAtividadeTecnica.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, BigInteger> {
    Optional<UserModel> findByLogin(String login);
}
