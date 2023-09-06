package com.nicollas.esigAtividadeTecnica.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordRequestDTO {

    @NotBlank(message = "O campo nova senha não pode ser vazio!")
    private String newPassword;

    @NotBlank(message = "O campo de confirmação de senha não pode ser vazio!")
    private String confirmPassword;

}
