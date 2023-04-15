package com.cailou.basicRegisterLoginAPI.dto.user;

import com.cailou.basicRegisterLoginAPI.dto.roles.RolesResponseDTO;
import com.cailou.basicRegisterLoginAPI.model.UserModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Data
public class UserResponseDTO {
    private UUID id;
    private String login;
    private boolean status;
    private boolean active;
    private boolean adminInterface;
    private String name;
    private String email;
    private String cellphone;
    private RolesResponseDTO role;

    public static UserResponseDTO convertToDto(UserModel user) {
        var userResponseDTO = new UserResponseDTO();

        userResponseDTO.setId(user.getId());

        userResponseDTO.setLogin(user.getLogin());
        userResponseDTO.setActive(user.isActive());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setCellphone(user.getCellphone());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setRole(RolesResponseDTO.convertToDto(user.getRole()));


        return userResponseDTO;
    }
}
