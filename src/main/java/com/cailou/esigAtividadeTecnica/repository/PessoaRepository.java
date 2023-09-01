package com.cailou.esigAtividadeTecnica.repository;

import com.cailou.esigAtividadeTecnica.model.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PessoaRepository extends JpaRepository<PessoaModel, UUID> {
}
