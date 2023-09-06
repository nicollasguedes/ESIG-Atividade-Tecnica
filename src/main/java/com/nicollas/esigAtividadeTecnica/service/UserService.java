package com.nicollas.esigAtividadeTecnica.service;


import com.nicollas.esigAtividadeTecnica.dto.user.PasswordRequestDTO;
import com.nicollas.esigAtividadeTecnica.dto.user.UserRequestDTO;
import com.nicollas.esigAtividadeTecnica.model.UserModel;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.text.ParseException;

public interface UserService {

    @Transactional
    UserModel saveUser(UserRequestDTO userRequest) throws ParseException;

    @Transactional
    UserModel updateUserPassword(BigInteger userId, PasswordRequestDTO passwordRequest);

    @Transactional
    Boolean deleteUser(BigInteger userId);

    UserModel listUser(BigInteger userId);

    UserModel switchUserActive(BigInteger userId);
}
