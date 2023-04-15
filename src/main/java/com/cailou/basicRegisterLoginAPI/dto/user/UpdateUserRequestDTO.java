package com.cailou.basicRegisterLoginAPI.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequestDTO {

    @NotBlank(message = "O campo nome não pode ser vazio!")
    private String name;

    @NotBlank(message = "O campo email não pode ser vazio!")
    private String email;

    @NotBlank(message = "O campo celular não pode ser vazio!")
    private String cellphone;

    private String roleId;
}
