package com.nicollas.esigAtividadeTecnica.repository;

import com.nicollas.esigAtividadeTecnica.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, BigInteger> {
    Optional<UserModel> findByLogin(String login);

    @Transactional
    default Boolean deleteByIdAndReturnBool(BigInteger id) {
        try {
            deleteById(id);
            return true; // Exclusão bem-sucedida
        } catch (Exception e) {
            return false; // Falha na exclusão
        }
    }
}
