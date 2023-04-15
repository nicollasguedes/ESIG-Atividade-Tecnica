package com.cailou.basicRegisterLoginAPI.service;

public interface PasswordService {
    void sendForgetPasswordToken(String email);

    String checkForgetPasswordToken(String token, String email);

    void resetPassword(String password, String confirmPassword, String resetPasswordToken);
}