package com.cailou.basicRegisterLoginAPI.dto.roles;

import com.cailou.basicRegisterLoginAPI.model.RoleModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Data
public class RolesResponseDTO {
    private UUID id;
    private String name;

    public static RolesResponseDTO convertToDto(RoleModel roleModel){
        var roleResponseDTO = new RolesResponseDTO();

        roleResponseDTO.setId(roleModel.getId());
        roleResponseDTO.setName(roleModel.getName());

        return roleResponseDTO;

    }
}
