package com.cailou.basicRegisterLoginAPI.repository;

import com.cailou.basicRegisterLoginAPI.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    Optional<UserModel> findByLogin(String login);

    Optional<UserModel> findByEmail(String email);

    Optional<UserModel> findByCellphone(String cellPhone);
}
