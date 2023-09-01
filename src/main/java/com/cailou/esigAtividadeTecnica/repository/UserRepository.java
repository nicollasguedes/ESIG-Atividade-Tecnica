package com.cailou.esigAtividadeTecnica.repository;

import com.cailou.esigAtividadeTecnica.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    Optional<UserModel> findByLogin(String login);
}
