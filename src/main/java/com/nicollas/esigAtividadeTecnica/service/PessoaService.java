package com.nicollas.esigAtividadeTecnica.service;


import com.nicollas.esigAtividadeTecnica.dto.pessoa.PessoaRequestDTO;
import com.nicollas.esigAtividadeTecnica.model.PessoaModel;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.List;

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

    List<PessoaModel> getPessoaList();

    List<PessoaModel> listPessoas();

    PessoaModel listPessoaByLogin(String login);
}
