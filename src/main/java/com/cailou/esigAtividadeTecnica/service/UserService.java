package com.cailou.esigAtividadeTecnica.service;


import com.cailou.esigAtividadeTecnica.dto.user.PasswordRequestDTO;
import com.cailou.esigAtividadeTecnica.dto.user.UserRequestDTO;
import com.cailou.esigAtividadeTecnica.model.UserModel;

import javax.transaction.Transactional;

public interface UserService {

    UserModel saveUser(UserRequestDTO userRequest);

    @Transactional
    UserModel updateUserPassword(String userId, PasswordRequestDTO passwordRequest);

    UserModel listUser(String userId);

    UserModel switchUserActivity(String userId);
}
