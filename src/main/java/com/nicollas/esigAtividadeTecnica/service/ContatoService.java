package com.nicollas.esigAtividadeTecnica.service;


import com.nicollas.esigAtividadeTecnica.dto.contato.ContatoRequestDTO;
import com.nicollas.esigAtividadeTecnica.model.ContatoModel;
import com.nicollas.esigAtividadeTecnica.model.PessoaModel;

import javax.transaction.Transactional;
import java.math.BigInteger;

public interface ContatoService {

    @Transactional
    ContatoModel saveContatoByPessoaId(BigInteger pessoaId, ContatoRequestDTO pessoaSalarioRequest);

    @Transactional
    ContatoModel saveContatoByPessoaModel(PessoaModel pessoa, ContatoRequestDTO pessoaSalarioRequest);

    @Transactional
    ContatoModel updateContatoByPessoaId(BigInteger pessoaId, ContatoRequestDTO pessoaSalarioRequest);

    @Transactional
    Boolean deleteContatoByPessoaId(BigInteger pessoaId);

    ContatoModel listContatoByPessoaId(BigInteger pessoaId);
}
