package com.cailou.esigAtividadeTecnica.dto.authentication;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AuthenticationDTO {
    @NotBlank(message = "O campo login não pode ser vazio!")
    private String login;


    @NotBlank(message = "O campo senha não pode ser vazio!")
    @Size(min = 8, max = 16, message = "A senha deve possuir no mínimo 8 e no máximo 16 caracteres!")
    private String password;
}
