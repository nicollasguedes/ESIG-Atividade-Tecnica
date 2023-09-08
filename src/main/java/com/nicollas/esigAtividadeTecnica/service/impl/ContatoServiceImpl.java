package com.nicollas.esigAtividadeTecnica.service.impl;


import com.nicollas.esigAtividadeTecnica.dto.contato.ContatoRequestDTO;
import com.nicollas.esigAtividadeTecnica.model.ContatoModel;
import com.nicollas.esigAtividadeTecnica.model.PessoaModel;
import com.nicollas.esigAtividadeTecnica.repository.ContatoRepository;
import com.nicollas.esigAtividadeTecnica.repository.PessoaRepository;
import com.nicollas.esigAtividadeTecnica.service.ContatoService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.Optional;

@Service
public class ContatoServiceImpl implements ContatoService {

    private final ContatoRepository contatoRepository;
    private final PessoaRepository pessoaRepository;


    public ContatoServiceImpl(ContatoRepository contatoRepository, PessoaRepository pessoaRepository) {
        this.contatoRepository = contatoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional
    @Override
    public ContatoModel saveContatoByPessoaId(BigInteger pessoaId, ContatoRequestDTO contatoRequest) {
        Optional<PessoaModel> pessoa = this.pessoaRepository.findById(pessoaId);
        if (pessoa.isEmpty()) {
            throw new RuntimeException("Pessoa não encontrada!");
        }

        ContatoModel contatoModel = new ContatoModel();

        contatoModel.setEmail(contatoRequest.getEmail());
        contatoModel.setTelefone(contatoRequest.getTelefone());
        contatoModel.setPessoa(pessoa.get());

        return this.contatoRepository.save(contatoModel);
    }

    @Transactional
    @Override
    public ContatoModel saveContatoByPessoaModel(PessoaModel pessoa, ContatoRequestDTO contatoRequest) {

        ContatoModel contatoModel = new ContatoModel();

        contatoModel.setEmail(contatoRequest.getEmail());
        contatoModel.setTelefone(contatoRequest.getTelefone());
        contatoModel.setPessoa(pessoa);

        return this.contatoRepository.save(contatoModel);
    }

    @Transactional
    @Override
    public ContatoModel updateContatoByPessoaId(BigInteger pessoaId, ContatoRequestDTO contatoRequest) {
        ContatoModel contatoModel = listContatoByPessoaId(pessoaId);

        contatoModel.setEmail(contatoRequest.getEmail());
        contatoModel.setTelefone(contatoRequest.getTelefone());

        return this.contatoRepository.save(contatoModel);
    }

    @Override
    @Transactional
    public Boolean deleteContatoByPessoaId(BigInteger pessoaId) {
        ContatoModel contatoFound = listContatoByPessoaId(pessoaId);
        PessoaModel pessoa = contatoFound.getPessoa();
        pessoa.setContato(null);
        pessoaRepository.save(pessoa);
        return contatoRepository.deleteByIdAndReturnBool(contatoFound.getId());
    }


    @Override
    public ContatoModel listContatoByPessoaId(BigInteger pessoaId) {
        Optional<ContatoModel> pessoa = this.contatoRepository.findByPessoaId(pessoaId);
        if (pessoa.isEmpty()) {
            throw new RuntimeException("Salario não encontrado!");
        }
        return pessoa.get();
    }
}
