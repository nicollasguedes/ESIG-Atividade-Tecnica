package com.cailou.basicRegisterLoginAPI.dto.user;

import com.cailou.basicRegisterLoginAPI.model.RoleModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Data
public class RoleResponseDTO {

    private UUID id;
    private String name;

    public static RoleResponseDTO convertToDto(RoleModel role) {
        var roleResponseDTO = new RoleResponseDTO();

        roleResponseDTO.setId(role.getId());
        roleResponseDTO.setName(role.getName());

        return roleResponseDTO;
    }


}
