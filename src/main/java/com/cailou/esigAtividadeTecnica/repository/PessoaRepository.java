package com.cailou.esigAtividadeTecnica.repository;

import com.cailou.esigAtividadeTecnica.model.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface PessoaRepository extends JpaRepository<PessoaModel, BigInteger> {
}
