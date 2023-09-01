package com.cailou.esigAtividadeTecnica.service;


import com.cailou.esigAtividadeTecnica.dto.pessoaSalario.PessoaSalarioRequestDTO;
import com.cailou.esigAtividadeTecnica.model.PessoaModel;
import com.cailou.esigAtividadeTecnica.model.PessoaSalarioModel;

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
