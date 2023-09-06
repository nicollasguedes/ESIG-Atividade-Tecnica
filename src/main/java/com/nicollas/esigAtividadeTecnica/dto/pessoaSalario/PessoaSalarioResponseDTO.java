package com.nicollas.esigAtividadeTecnica.dto.pessoaSalario;

import com.nicollas.esigAtividadeTecnica.model.PessoaSalarioModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@Data
public class PessoaSalarioResponseDTO {
    private BigInteger id;
    private String nome;
    private BigDecimal salario;
    private BigInteger pessoaId;


    public static PessoaSalarioResponseDTO convertToDto(PessoaSalarioModel pessoa) {
        var userResponseDTO = new PessoaSalarioResponseDTO();

        userResponseDTO.setId(pessoa.getId());
        userResponseDTO.setNome(pessoa.getNome());
        userResponseDTO.setSalario(pessoa.getSalario());
        userResponseDTO.setPessoaId(pessoa.getPessoa().getId());

        return userResponseDTO;
    }
}
