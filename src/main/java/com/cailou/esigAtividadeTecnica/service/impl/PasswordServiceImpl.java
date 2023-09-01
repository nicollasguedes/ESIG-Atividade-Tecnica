package com.cailou.esigAtividadeTecnica.service.impl;

import com.cailou.esigAtividadeTecnica.service.PasswordService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PasswordServiceImpl implements PasswordService {


    @Override
    public void sendForgetPasswordToken(String email) {

    }

    @Override
    public String checkForgetPasswordToken(String token, String email) {
        return null;
    }

    @Override
    public void resetPassword(String password, String confirmPassword, String resetPasswordToken) {

    }
    private static String generateToken(int len) {
        String chars = "0123456789";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));

        return sb.toString();
    }

}
