package com.nicollas.esigAtividadeTecnica.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckPasswordRequestDTO {

    @NotBlank(message = "O campo nova senha não pode ser vazio!")
    private String oldPassword;

}
