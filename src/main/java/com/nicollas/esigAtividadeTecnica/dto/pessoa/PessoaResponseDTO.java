package com.nicollas.esigAtividadeTecnica.dto.pessoa;

import com.nicollas.esigAtividadeTecnica.dto.contato.ContatoResponseDTO;
import com.nicollas.esigAtividadeTecnica.dto.endereco.EnderecoResponseDTO;
import com.nicollas.esigAtividadeTecnica.dto.pessoaSalario.PessoaSalarioResponseDTO;
import com.nicollas.esigAtividadeTecnica.model.PessoaModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.text.SimpleDateFormat;

@Getter
@Setter
@Data
public class PessoaResponseDTO {
    private BigInteger id;
    private String nome;
    private String dataNascimento;
    private int cargoId;
    private PessoaSalarioResponseDTO pessoaSalario = null;
    private EnderecoResponseDTO endereco = null;
    private ContatoResponseDTO contatoResponseDTO = null;

    public static PessoaResponseDTO convertToDto(PessoaModel pessoa) {
        var pessoaResponseDTO = new PessoaResponseDTO();

        pessoaResponseDTO.setId(pessoa.getId());
        pessoaResponseDTO.setNome(pessoa.getNome());
        pessoaResponseDTO.setCargoId(pessoa.getCargoId());

        if (pessoa.getPessoaSalario() != null) {
            pessoaResponseDTO.setPessoaSalario(
                    PessoaSalarioResponseDTO.convertToDto(pessoa.getPessoaSalario()));
        }

        if (pessoa.getEndereco() != null) {
            pessoaResponseDTO.setEndereco(
                    EnderecoResponseDTO.convertToDto(pessoa.getEndereco()));
        }

        if (pessoa.getContato() != null) {
            pessoaResponseDTO.setContatoResponseDTO(
                    ContatoResponseDTO.convertToDto(pessoa.getContato()));
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M/dd/yyyy");
        pessoaResponseDTO.setDataNascimento(simpleDateFormat.format(pessoa.getDataNascimento().getTime()));

        return pessoaResponseDTO;
    }
}
