package com.cailou.esigAtividadeTecnica.service.impl;


import com.cailou.esigAtividadeTecnica.dto.pessoa.PessoaRequestDTO;
import com.cailou.esigAtividadeTecnica.model.PessoaModel;
import com.cailou.esigAtividadeTecnica.repository.PessoaRepository;
import com.cailou.esigAtividadeTecnica.service.PessoaService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

@Service
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;
    private final PessoaSalarioServiceImpl pessoaSalarioService;


    public PessoaServiceImpl(PessoaRepository pessoaRepository, PessoaSalarioServiceImpl pessoaSalarioService) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaSalarioService = pessoaSalarioService;
    }

    @Transactional
    @Override
    public PessoaModel savePessoaByLogin(PessoaRequestDTO pessoaRequest, String login) throws ParseException {
        Optional<PessoaModel> pessoaAlreadyExists = this.pessoaRepository.findByLogin(login);
        if (pessoaAlreadyExists.isPresent()) {
            throw new RuntimeException("Login já existe, tente outro");
        }

        PessoaModel pessoaModel = new PessoaModel();

        pessoaModel.setNome(pessoaRequest.getNome());
        pessoaModel.setEmail(pessoaRequest.getEmail());
        pessoaModel.setCep(pessoaRequest.getCep());
        pessoaModel.setPais(pessoaRequest.getPais());
        pessoaModel.setCidade(pessoaRequest.getCidade());
        pessoaModel.setEndereco(pessoaRequest.getEndereco());
        pessoaModel.setTelefone(pessoaRequest.getTelefone());
        pessoaModel.setCargoId(pessoaRequest.getCargoId());
        pessoaModel.setLogin(login);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M/dd/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(simpleDateFormat.parse(pessoaRequest.getDataNascimento()));
        pessoaModel.setDataNascimento(calendar);

        if (pessoaRequest.getPessoaSalarioRequest() != null) {
            var salario = pessoaSalarioService.savePessoaSalarioByPessoaModel(
                    pessoaModel, pessoaRequest.getPessoaSalarioRequest());
            pessoaModel.setPessoaSalario(salario);
        }

        return this.pessoaRepository.save(pessoaModel);
    }

    @Override
    public PessoaModel updatePessoaByPessoaId(BigInteger pessoaId, PessoaRequestDTO pessoaRequest) throws ParseException {
        PessoaModel pessoaModel = listPessoa(pessoaId);

        pessoaModel.setNome(pessoaRequest.getNome());
        pessoaModel.setEmail(pessoaRequest.getEmail());
        pessoaModel.setCep(pessoaRequest.getCep());
        pessoaModel.setPais(pessoaRequest.getPais());
        pessoaModel.setCidade(pessoaRequest.getCidade());
        pessoaModel.setEndereco(pessoaRequest.getEndereco());
        pessoaModel.setTelefone(pessoaRequest.getTelefone());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M/dd/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(simpleDateFormat.parse(pessoaRequest.getDataNascimento()));
        pessoaModel.setDataNascimento(calendar);
        pessoaModel.setCargoId(pessoaRequest.getCargoId());

        if (pessoaRequest.getPessoaSalarioRequest() != null) {
            var salario = pessoaSalarioService.savePessoaSalarioByPessoaModel(
                    pessoaModel, pessoaRequest.getPessoaSalarioRequest());
            pessoaModel.setPessoaSalario(salario);
        }

        return this.pessoaRepository.save(pessoaModel);
    }

    @Override
    @Transactional
    public Boolean deletePessoaByLogin(String login) {
        var pessoaFound = listPessoaByLogin(login);
        if (pessoaFound.getPessoaSalario() != null){
            pessoaSalarioService.deletePessoaSalarioByPessoaId(pessoaFound.getId());
        }
        return pessoaRepository.deleteByIdAndReturnBool(pessoaFound.getId());
    }

    @Override
    @Transactional
    public Boolean deletePessoaByPessoaId(BigInteger pessoaId) {
        var pessoaFound = listPessoa(pessoaId);
        return pessoaRepository.deleteByIdAndReturnBool(pessoaFound.getId());
    }

    @Override
    public PessoaModel listPessoa(BigInteger pessoaId) {
        Optional<PessoaModel> pessoa = this.pessoaRepository.findById(pessoaId);
        if (pessoa.isEmpty()) {
            throw new RuntimeException("Pessoa não encontrada!");
        }
        return pessoa.get();
    }

    @Override
    public PessoaModel listPessoaByLogin(String login) {
        Optional<PessoaModel> pessoa = this.pessoaRepository.findByLogin(login);
        if (pessoa.isEmpty()) {
            throw new RuntimeException("Pessoa não encontrada!");
        }
        return pessoa.get();
    }
}
