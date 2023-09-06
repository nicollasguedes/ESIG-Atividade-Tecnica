package com.nicollas.esigAtividadeTecnica.service;


import com.nicollas.esigAtividadeTecnica.dto.pessoaSalario.PessoaSalarioRequestDTO;
import com.nicollas.esigAtividadeTecnica.model.PessoaModel;
import com.nicollas.esigAtividadeTecnica.model.PessoaSalarioModel;

import javax.transaction.Transactional;
import java.math.BigInteger;

public interface PessoaSalarioService {

    @Transactional
    PessoaSalarioModel savePessoaSalarioByPessoaId(BigInteger pessoaId, PessoaSalarioRequestDTO pessoaSalarioRequest);

    @Transactional
    PessoaSalarioModel savePessoaSalarioByPessoaModel(PessoaModel pessoa, PessoaSalarioRequestDTO pessoaSalarioRequest);

    @Transactional
    PessoaSalarioModel updatePessoaSalarioByPessoaId(BigInteger pessoaId, PessoaSalarioRequestDTO pessoaSalarioRequest);

    @Transactional
    Boolean deletePessoaSalarioByPessoaId(BigInteger pessoaId);

    PessoaSalarioModel listPessoaSalarioByPessoaId(BigInteger pessoaId);
}
