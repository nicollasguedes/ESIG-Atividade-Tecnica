package com.cailou.basicRegisterLoginAPI.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cailou.basicRegisterLoginAPI.detail.UserDetailData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.cailou.basicRegisterLoginAPI.model.UserModel;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        UserModel userMOdel;

        try {
            userMOdel = new ObjectMapper().readValue(request.getInputStream(), UserModel.class);
        } catch (IOException e) {
            throw new RuntimeException("Login ou senha incorretos!");
        }

        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userMOdel.getLogin(),
                userMOdel.getPassword()
        ));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException {
        UserDetailData userDetailData = (UserDetailData) authResult.getPrincipal();

        String jwtSecret = "01075df752c01ba95dee395efa7b6304";
        String accessToken = JWT.create()
                .withSubject(userDetailData.getId())
                .withExpiresAt(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000))
                .sign(Algorithm.HMAC256(jwtSecret));

        String refreshToken = JWT.create()
                .withSubject(userDetailData.getId())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000))
                .sign(Algorithm.HMAC256(jwtSecret));



        String roles = String.valueOf(userDetailData.getAuthorities());
        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);
        tokens.put("acess_roles", roles);
        tokens.put("user_id", userDetailData.getId());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        Map<String, String> tokens = new HashMap<>();
        tokens.put("error", "Login ou Senha invalidos!");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(401);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}
