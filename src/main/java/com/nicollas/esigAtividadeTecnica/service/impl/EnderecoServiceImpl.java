package com.nicollas.esigAtividadeTecnica.service.impl;


import com.nicollas.esigAtividadeTecnica.dto.endereco.EnderecoRequestDTO;
import com.nicollas.esigAtividadeTecnica.model.PessoaModel;
import com.nicollas.esigAtividadeTecnica.model.EnderecoModel;
import com.nicollas.esigAtividadeTecnica.repository.PessoaRepository;
import com.nicollas.esigAtividadeTecnica.repository.EnderecoRepository;
import com.nicollas.esigAtividadeTecnica.service.EnderecoService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.Optional;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final PessoaRepository pessoaRepository;


    public EnderecoServiceImpl(EnderecoRepository enderecoRepository, PessoaRepository pessoaRepository) {
        this.enderecoRepository = enderecoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional
    @Override
    public EnderecoModel saveEnderecoByPessoaId(BigInteger pessoaId, EnderecoRequestDTO enderecoRequest) {
        Optional<PessoaModel> pessoa = this.pessoaRepository.findById(pessoaId);
        if (pessoa.isEmpty()) {
            throw new RuntimeException("Pessoa não encontrada!");
        }

        EnderecoModel enderecoModel = new EnderecoModel();

        enderecoModel.setCep(enderecoRequest.getCep());
        enderecoModel.setPais(enderecoRequest.getPais());
        enderecoModel.setCidade(enderecoRequest.getCidade());
        enderecoModel.setEndereco(enderecoRequest.getEndereco());
        enderecoModel.setPessoa(pessoa.get());

        return this.enderecoRepository.save(enderecoModel);
    }

    @Transactional
    @Override
    public EnderecoModel saveEnderecoByPessoaModel(PessoaModel pessoa, EnderecoRequestDTO enderecoRequest) {

        EnderecoModel enderecoModel = new EnderecoModel();

        enderecoModel.setCep(enderecoRequest.getCep());
        enderecoModel.setPais(enderecoRequest.getPais());
        enderecoModel.setCidade(enderecoRequest.getCidade());
        enderecoModel.setEndereco(enderecoRequest.getEndereco());
        enderecoModel.setPessoa(pessoa);

        return this.enderecoRepository.save(enderecoModel);
    }

    @Transactional
    @Override
    public EnderecoModel updateEnderecoByPessoaId(BigInteger pessoaId, EnderecoRequestDTO enderecoRequest) {
        EnderecoModel enderecoModel = listEnderecoByPessoaId(pessoaId);

        enderecoModel.setCep(enderecoRequest.getCep());
        enderecoModel.setPais(enderecoRequest.getPais());
        enderecoModel.setCidade(enderecoRequest.getCidade());
        enderecoModel.setEndereco(enderecoRequest.getEndereco());

        return this.enderecoRepository.save(enderecoModel);
    }

    @Override
    @Transactional
    public Boolean deleteEnderecoByPessoaId(BigInteger pessoaId) {
        EnderecoModel enderecoFound = listEnderecoByPessoaId(pessoaId);
        PessoaModel pessoa = enderecoFound.getPessoa();
        pessoa.setEndereco(null);
        pessoaRepository.save(pessoa);
        return enderecoRepository.deleteByIdAndReturnBool(enderecoFound.getId());
    }


    @Override
    public EnderecoModel listEnderecoByPessoaId(BigInteger pessoaId) {
        Optional<EnderecoModel> pessoa = this.enderecoRepository.findByPessoaId(pessoaId);
        if (pessoa.isEmpty()) {
            throw new RuntimeException("Salario não encontrado!");
        }
        return pessoa.get();
    }
}
