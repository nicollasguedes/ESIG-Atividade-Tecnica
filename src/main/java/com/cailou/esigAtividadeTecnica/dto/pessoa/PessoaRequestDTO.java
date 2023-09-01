package com.cailou.esigAtividadeTecnica.dto.pessoa;

import com.cailou.esigAtividadeTecnica.dto.pessoaSalario.PessoaSalarioRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaRequestDTO {

    @NotBlank(message = "O campo nome não pode ser vazio!")
    private String nome;

    @NotBlank(message = "O campo nome não pode ser vazio!")
    private String email;

    @NotBlank(message = "O campo cep não pode ser vazio!")
    private String cep;

    @NotBlank(message = "O campo país não pode ser vazio!")
    private String pais;

    @NotBlank(message = "O campo Cidade não pode ser vazio!")
    private String cidade;

    @NotBlank(message = "O campo Endereço não pode ser vazio!")
    private String endereco;

    @NotBlank(message = "O campo telefone não pode ser vazio!")
    private String telefone;

    @NotNull(message = "O campo Data de Nascimento não pode ser vazio! M/dd/yyyy")
    private String dataNascimento;

    @NotNull(message = "O campo CargoId não pode ser vazio!")
    private int cargoId;

    private PessoaSalarioRequestDTO pessoaSalarioRequest = null;
}
