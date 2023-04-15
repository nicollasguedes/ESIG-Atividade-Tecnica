package com.cailou.basicRegisterLoginAPI.service;


import com.cailou.basicRegisterLoginAPI.dto.user.PasswordRequestDTO;
import com.cailou.basicRegisterLoginAPI.dto.user.UpdateUserRequestDTO;
import com.cailou.basicRegisterLoginAPI.dto.user.UserRequestDTO;
import com.cailou.basicRegisterLoginAPI.model.RoleModel;
import com.cailou.basicRegisterLoginAPI.model.UserModel;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {

    UserModel saveUser(UserRequestDTO userRequest);

    @Transactional
    UserModel updatePassword(String userId, PasswordRequestDTO passwordRequest);

    @Transactional
    UserModel updateUser(String userId, UpdateUserRequestDTO userRequest);

    UserModel listUser(String userId);

    UserModel switchUserActivity(String userId);

    List<RoleModel> listAllRoles();
}
