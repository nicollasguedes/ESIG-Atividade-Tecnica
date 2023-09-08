package com.nicollas.esigAtividadeTecnica.repository;

import com.nicollas.esigAtividadeTecnica.model.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.math.BigInteger;

public interface PessoaRepository extends JpaRepository<PessoaModel, BigInteger> {

    @Transactional
    default Boolean deleteByIdAndReturnBool(BigInteger id) {
        try {
            deleteById(id);
            return true; // Exclusão bem-sucedida
        } catch (Exception e) {
            return false; // Falha na exclusão
        }
    }
}
