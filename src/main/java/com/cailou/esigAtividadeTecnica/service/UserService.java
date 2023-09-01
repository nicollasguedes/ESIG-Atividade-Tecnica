package com.cailou.esigAtividadeTecnica.service;


import com.cailou.esigAtividadeTecnica.dto.user.PasswordRequestDTO;
import com.cailou.esigAtividadeTecnica.dto.user.UserRequestDTO;
import com.cailou.esigAtividadeTecnica.model.UserModel;

import javax.transaction.Transactional;
import java.math.BigInteger;

public interface UserService {

    UserModel saveUser(UserRequestDTO userRequest);

    @Transactional
    UserModel updateUserPassword(BigInteger userId, PasswordRequestDTO passwordRequest);

    UserModel listUser(BigInteger userId);

    UserModel switchUserActivity(BigInteger userId);
}
