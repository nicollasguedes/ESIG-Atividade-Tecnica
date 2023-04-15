package com.cailou.basicRegisterLoginAPI.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JwtTokenUtil {

    public String generateAcessToken(UUID userId, String email) {

        return JWT.create()
                .withSubject(email)
                .withClaim("USER_ID", String.valueOf(userId))
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .sign(getAlgorithm());
    }

    public String generateRefreshToken(UUID userId, String email) {

        return JWT.create()
                .withSubject(email)
                .withClaim("USER_ID", String.valueOf(userId))
                .withExpiresAt(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000))

                .sign(getAlgorithm());
    }

    public boolean tokenIsValid(String token) {
        try {
            JWT.require(getAlgorithm()).build().verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public String getUserEmail(String token) {
        return JWT.require(getAlgorithm()).build().verify(token).getSubject();
    }

    public String getUserId(String token) {
        return JWT.require(getAlgorithm()).build().verify(token).getClaim("USER_ID").asString();
    }

    public Algorithm getAlgorithm() {
        // todo: usar uma chave real usango environment variables
        return Algorithm.HMAC256("secret".getBytes());
    }
}
