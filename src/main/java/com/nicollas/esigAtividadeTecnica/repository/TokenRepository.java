package com.nicollas.esigAtividadeTecnica.repository;

import com.nicollas.esigAtividadeTecnica.model.TokenModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<TokenModel, Long> {
}
