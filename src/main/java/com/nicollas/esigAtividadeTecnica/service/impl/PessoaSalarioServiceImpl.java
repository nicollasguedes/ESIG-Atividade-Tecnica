package com.nicollas.esigAtividadeTecnica.service.impl;


import com.nicollas.esigAtividadeTecnica.dto.pessoaSalario.PessoaSalarioRequestDTO;
import com.nicollas.esigAtividadeTecnica.model.PessoaModel;
import com.nicollas.esigAtividadeTecnica.model.PessoaSalarioModel;
import com.nicollas.esigAtividadeTecnica.repository.PessoaRepository;
import com.nicollas.esigAtividadeTecnica.repository.PessoaSalarioRepository;
import com.nicollas.esigAtividadeTecnica.service.PessoaSalarioService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.Optional;

@Service
public class PessoaSalarioServiceImpl implements PessoaSalarioService {

    private final PessoaSalarioRepository pessoaSalarioRepository;
    private final PessoaRepository pessoaRepository;


    public PessoaSalarioServiceImpl(PessoaSalarioRepository pessoaSalarioRepository, PessoaRepository pessoaRepository) {
        this.pessoaSalarioRepository = pessoaSalarioRepository;
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional
    @Override
    public PessoaSalarioModel savePessoaSalarioByPessoaId(BigInteger pessoaId, PessoaSalarioRequestDTO pessoaSalarioRequest) {
        Optional<PessoaModel> pessoa = this.pessoaRepository.findById(pessoaId);
        if (pessoa.isEmpty()) {
            throw new RuntimeException("Pessoa não encontrada!");
        }

        PessoaSalarioModel pessoaSalarioModel = new PessoaSalarioModel();

        pessoaSalarioModel.setSalario(pessoaSalarioRequest.getSalario());
        pessoaSalarioModel.setNome(pessoa.get().getNome());
        pessoaSalarioModel.setPessoa(pessoa.get());

        return this.pessoaSalarioRepository.save(pessoaSalarioModel);
    }

    @Transactional
    @Override
    public PessoaSalarioModel savePessoaSalarioByPessoaModel(PessoaModel pessoa, PessoaSalarioRequestDTO pessoaSalarioRequest) {

        PessoaSalarioModel pessoaSalarioModel = new PessoaSalarioModel();

        pessoaSalarioModel.setSalario(pessoaSalarioRequest.getSalario());
        pessoaSalarioModel.setNome(pessoa.getNome());
        pessoaSalarioModel.setPessoa(pessoa);

        return this.pessoaSalarioRepository.save(pessoaSalarioModel);
    }

    @Transactional
    @Override
    public PessoaSalarioModel updatePessoaSalarioByPessoaId(BigInteger pessoaId, PessoaSalarioRequestDTO pessoaSalarioRequest) {
        PessoaSalarioModel pessoaSalarioModel = listPessoaSalarioByPessoaId(pessoaId);

        pessoaSalarioModel.setSalario(pessoaSalarioRequest.getSalario());

        return this.pessoaSalarioRepository.save(pessoaSalarioModel);
    }

    @Override
    @Transactional
    public Boolean deletePessoaSalarioByPessoaId(BigInteger pessoaId) {
        PessoaSalarioModel pessoaSalarioFound = listPessoaSalarioByPessoaId(pessoaId);
        PessoaModel pessoa = pessoaSalarioFound.getPessoa();
        pessoa.setPessoaSalario(null);
        pessoaRepository.save(pessoa);
        return pessoaSalarioRepository.deleteByIdAndReturnBool(pessoaSalarioFound.getId());
    }


    @Override
    public PessoaSalarioModel listPessoaSalarioByPessoaId(BigInteger pessoaId) {
        Optional<PessoaSalarioModel> pessoa = this.pessoaSalarioRepository.findByPessoaId(pessoaId);
        if (pessoa.isEmpty()) {
            throw new RuntimeException("Salario não encontrado!");
        }
        return pessoa.get();
    }
}
