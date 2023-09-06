package com.nicollas.esigAtividadeTecnica.dto.user;

import com.nicollas.esigAtividadeTecnica.dto.pessoa.PessoaResponseDTO;
import com.nicollas.esigAtividadeTecnica.model.UserModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@Data
public class UserResponseDTO {
    private BigInteger id;
    private String login;
    private boolean active;
    private PessoaResponseDTO pessoa = null;

    public static UserResponseDTO convertToDto(UserModel user) {
        var userResponseDTO = new UserResponseDTO();

        userResponseDTO.setId(user.getId());
        userResponseDTO.setLogin(user.getLogin());
        userResponseDTO.setActive(user.isActive());

        return userResponseDTO;
    }
}
