package com.cailou.basicRegisterLoginAPI.dto.password;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordTokenDTO {
    private String token;
    private String email;
    private String password;
    private String confirmPassword;
}
