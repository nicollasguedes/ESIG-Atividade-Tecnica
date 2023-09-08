package com.nicollas.esigAtividadeTecnica.service;


import com.nicollas.esigAtividadeTecnica.dto.endereco.EnderecoRequestDTO;
import com.nicollas.esigAtividadeTecnica.model.EnderecoModel;
import com.nicollas.esigAtividadeTecnica.model.PessoaModel;

import javax.transaction.Transactional;
import java.math.BigInteger;

public interface EnderecoService {

    @Transactional
    EnderecoModel saveEnderecoByPessoaId(BigInteger pessoaId, EnderecoRequestDTO pessoaSalarioRequest);

    @Transactional
    EnderecoModel saveEnderecoByPessoaModel(PessoaModel pessoa, EnderecoRequestDTO pessoaSalarioRequest);

    @Transactional
    EnderecoModel updateEnderecoByPessoaId(BigInteger pessoaId, EnderecoRequestDTO pessoaSalarioRequest);

    @Transactional
    Boolean deleteEnderecoByPessoaId(BigInteger pessoaId);

    EnderecoModel listEnderecoByPessoaId(BigInteger pessoaId);
}
