package com.cailou.esigAtividadeTecnica.service;


import com.cailou.esigAtividadeTecnica.dto.pessoa.PessoaRequestDTO;
import com.cailou.esigAtividadeTecnica.model.PessoaModel;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.text.ParseException;

public interface PessoaService {

    @Transactional
    PessoaModel savePessoaByLogin(PessoaRequestDTO pessoaRequest, String login) throws ParseException;

    @Transactional
    PessoaModel updatePessoaByPessoaId(BigInteger pessoaId, PessoaRequestDTO pessoaRequest) throws ParseException;

    @Transactional
    Boolean deletePessoaByLogin(String Login);

    @Transactional
    Boolean deletePessoaByPessoaId(BigInteger pessoaId);

    PessoaModel listPessoa(BigInteger pessoaId);

    PessoaModel listPessoaByLogin(String login);
}
