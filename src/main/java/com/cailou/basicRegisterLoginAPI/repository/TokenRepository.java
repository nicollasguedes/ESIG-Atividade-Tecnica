package com.cailou.basicRegisterLoginAPI.repository;

import com.cailou.basicRegisterLoginAPI.model.TokenModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<TokenModel, Long> {
}
