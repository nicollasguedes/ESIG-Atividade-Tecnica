package com.cailou.esigAtividadeTecnica.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    @NotBlank(message = "O campo login não pode ser vazio!")
    private String login;

    @NotBlank(message = "O campo senha não pode ser vazio!")
    private String password;

    @NotBlank(message = "O campo nome não pode ser vazio!")
    private String name;

    @NotNull(message = "O campo active não pode ser vazio!")
    private boolean active;

    private String roleId;
}
