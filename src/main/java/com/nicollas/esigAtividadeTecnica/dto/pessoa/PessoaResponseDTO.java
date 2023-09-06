package com.nicollas.esigAtividadeTecnica.dto.pessoa;

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
    private String email;
    private String cep;
    private String pais;
    private String cidade;
    private String endereco;
    private String telefone;
    private String login;
    private String dataNascimento;
    private int cargoId;
    private PessoaSalarioResponseDTO pessoaSalario = null;

    public static PessoaResponseDTO convertToDto(PessoaModel pessoa) {
        var pessoaResponseDTO = new PessoaResponseDTO();

        pessoaResponseDTO.setId(pessoa.getId());
        pessoaResponseDTO.setNome(pessoa.getNome());
        pessoaResponseDTO.setEmail(pessoa.getEmail());
        pessoaResponseDTO.setCep(pessoa.getCep());
        pessoaResponseDTO.setPais(pessoa.getPais());
        pessoaResponseDTO.setCidade(pessoa.getCidade());
        pessoaResponseDTO.setEndereco(pessoa.getEndereco());
        pessoaResponseDTO.setTelefone(pessoa.getTelefone());
        pessoaResponseDTO.setTelefone(pessoa.getTelefone());
        pessoaResponseDTO.setLogin(pessoa.getLogin());
        pessoaResponseDTO.setCargoId(pessoa.getCargoId());

        if (pessoa.getPessoaSalario() != null) {
            pessoaResponseDTO.setPessoaSalario(
                    PessoaSalarioResponseDTO.convertToDto(pessoa.getPessoaSalario()));
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M/dd/yyyy");
        pessoaResponseDTO.setDataNascimento(simpleDateFormat.format(pessoa.getDataNascimento().getTime()));

        return pessoaResponseDTO;
    }
}
