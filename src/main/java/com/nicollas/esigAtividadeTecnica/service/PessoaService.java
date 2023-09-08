package com.nicollas.esigAtividadeTecnica.service;


import com.nicollas.esigAtividadeTecnica.dto.pessoa.PessoaRequestDTO;
import com.nicollas.esigAtividadeTecnica.model.PessoaModel;
import com.nicollas.esigAtividadeTecnica.model.UserModel;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.text.ParseException;

public interface PessoaService {

    @Transactional
    PessoaModel savePessoaByUserModel(UserModel user, PessoaRequestDTO pessoaRequest) throws ParseException;
    @Transactional
    PessoaModel savePessoaByUser(PessoaRequestDTO pessoaRequest, UserModel user) throws ParseException;

    @Transactional
    PessoaModel updatePessoaByPessoaId(BigInteger pessoaId, PessoaRequestDTO pessoaRequest) throws ParseException;

    @Transactional
    Boolean deletePessoaByPessoaId(BigInteger pessoaId);

    PessoaModel listPessoa(BigInteger pessoaId);

}
