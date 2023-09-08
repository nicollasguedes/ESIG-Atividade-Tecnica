package com.nicollas.esigAtividadeTecnica.service.impl;


import com.nicollas.esigAtividadeTecnica.dto.pessoa.PessoaRequestDTO;
import com.nicollas.esigAtividadeTecnica.model.PessoaModel;
import com.nicollas.esigAtividadeTecnica.model.UserModel;
import com.nicollas.esigAtividadeTecnica.repository.PessoaRepository;
import com.nicollas.esigAtividadeTecnica.service.PessoaService;
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
    private final EnderecoServiceImpl enderecoService;
    private final ContatoServiceImpl contatoService;



    public PessoaServiceImpl(PessoaRepository pessoaRepository, PessoaSalarioServiceImpl pessoaSalarioService, EnderecoServiceImpl enderecoService, ContatoServiceImpl contatoService) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaSalarioService = pessoaSalarioService;
        this.enderecoService = enderecoService;
        this.contatoService = contatoService;

    }

    @Override
    public PessoaModel savePessoaByUserModel(UserModel user, PessoaRequestDTO pessoaRequest) throws ParseException {
        PessoaModel pessoaModel = new PessoaModel();

        pessoaModel.setNome(pessoaRequest.getNome());
        pessoaModel.setCargoId(pessoaRequest.getCargoId());
        pessoaModel.setUser(user);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M/dd/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(simpleDateFormat.parse(pessoaRequest.getDataNascimento()));
        pessoaModel.setDataNascimento(calendar);

        pessoaModel = this.pessoaRepository.save(pessoaModel);

        if (pessoaRequest.getPessoaSalarioRequest() != null) {
            var salario = pessoaSalarioService.savePessoaSalarioByPessoaModel(
                    pessoaModel, pessoaRequest.getPessoaSalarioRequest());
            pessoaModel.setPessoaSalario(salario);
        }

        if (pessoaRequest.getEnderecoRequest() != null) {
            var endereco = enderecoService.saveEnderecoByPessoaModel(
                    pessoaModel, pessoaRequest.getEnderecoRequest());
            pessoaModel.setEndereco(endereco);
        }

        if (pessoaRequest.getContatoRequest() != null) {
            var contato = contatoService.saveContatoByPessoaModel(
                    pessoaModel, pessoaRequest.getContatoRequest());
            pessoaModel.setContato(contato);
        }


        return this.pessoaRepository.save(pessoaModel);
    }

    @Transactional
    @Override
    public PessoaModel savePessoaByUser(PessoaRequestDTO pessoaRequest, UserModel user) throws ParseException {
        PessoaModel pessoaModel = new PessoaModel();

        pessoaModel.setNome(pessoaRequest.getNome());
        pessoaModel.setCargoId(pessoaRequest.getCargoId());
        pessoaModel.setUser(user);


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M/dd/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(simpleDateFormat.parse(pessoaRequest.getDataNascimento()));
        pessoaModel.setDataNascimento(calendar);

        pessoaModel = this.pessoaRepository.save(pessoaModel);

        if (pessoaRequest.getPessoaSalarioRequest() != null) {
            var salario = pessoaSalarioService.savePessoaSalarioByPessoaModel(
                    pessoaModel, pessoaRequest.getPessoaSalarioRequest());
            pessoaModel.setPessoaSalario(salario);
        }

        if (pessoaRequest.getEnderecoRequest() != null) {
            var endereco = enderecoService.saveEnderecoByPessoaModel(
                    pessoaModel, pessoaRequest.getEnderecoRequest());
            pessoaModel.setEndereco(endereco);
        }

        if (pessoaRequest.getContatoRequest() != null) {
            var contato = contatoService.saveContatoByPessoaModel(
                    pessoaModel, pessoaRequest.getContatoRequest());
            pessoaModel.setContato(contato);
        }


        return this.pessoaRepository.save(pessoaModel);
    }

    @Override
    public PessoaModel updatePessoaByPessoaId(BigInteger pessoaId, PessoaRequestDTO pessoaRequest) throws ParseException {
        PessoaModel pessoaModel = listPessoa(pessoaId);

        pessoaModel.setNome(pessoaRequest.getNome());
        pessoaModel.setCargoId(pessoaRequest.getCargoId());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M/dd/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(simpleDateFormat.parse(pessoaRequest.getDataNascimento()));
        pessoaModel.setDataNascimento(calendar);
        pessoaModel.setCargoId(pessoaRequest.getCargoId());

        if (pessoaRequest.getPessoaSalarioRequest() != null && pessoaModel.getPessoaSalario() != null) {
            var salario = pessoaSalarioService.updatePessoaSalarioByPessoaId(
                    pessoaModel.getId(), pessoaRequest.getPessoaSalarioRequest());
            pessoaModel.setPessoaSalario(salario);
        } else if (pessoaRequest.getPessoaSalarioRequest() != null) {
            var salario = pessoaSalarioService.savePessoaSalarioByPessoaId(
                    pessoaModel.getId(), pessoaRequest.getPessoaSalarioRequest());
            pessoaModel.setPessoaSalario(salario);
        }

        if (pessoaRequest.getEnderecoRequest() != null && pessoaModel.getEndereco() != null) {
            var endereco = enderecoService.updateEnderecoByPessoaId(
                    pessoaModel.getId(), pessoaRequest.getEnderecoRequest());
            pessoaModel.setEndereco(endereco);
        } else if (pessoaRequest.getEnderecoRequest() != null) {
            var endereco = enderecoService.saveEnderecoByPessoaId(
                    pessoaModel.getId(), pessoaRequest.getEnderecoRequest());
            pessoaModel.setEndereco(endereco);
        }

        if (pessoaRequest.getContatoRequest() != null && pessoaModel.getContato() != null) {
            var contato = contatoService.updateContatoByPessoaId(
                    pessoaModel.getId(), pessoaRequest.getContatoRequest());
            pessoaModel.setContato(contato);
        } else if (pessoaRequest.getEnderecoRequest() != null) {
            var contato = contatoService.saveContatoByPessoaId(
                    pessoaModel.getId(), pessoaRequest.getContatoRequest());
            pessoaModel.setContato(contato);
        }

        return this.pessoaRepository.save(pessoaModel);
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


}
