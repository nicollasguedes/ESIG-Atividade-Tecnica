package com.cailou.esigAtividadeTecnica.repository;

import com.cailou.esigAtividadeTecnica.model.TokenModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface TokenRepository extends JpaRepository<TokenModel, BigInteger> {
}
