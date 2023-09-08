package com.nicollas.esigAtividadeTecnica.dto.pessoa;

import com.nicollas.esigAtividadeTecnica.dto.contato.ContatoRequestDTO;
import com.nicollas.esigAtividadeTecnica.dto.endereco.EnderecoRequestDTO;
import com.nicollas.esigAtividadeTecnica.dto.pessoaSalario.PessoaSalarioRequestDTO;
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

    @NotNull(message = "O campo Data de Nascimento não pode ser vazio! M/dd/yyyy")
    private String dataNascimento;

    @NotNull(message = "O campo CargoId não pode ser vazio!")
    private int cargoId;

    @NotNull(message = "O campo endereco não pode ser vazio!")
    private EnderecoRequestDTO enderecoRequest;

    @NotNull(message = "O campo contato não pode ser vazio!")
    private ContatoRequestDTO contatoRequest;

    private PessoaSalarioRequestDTO pessoaSalarioRequest = null;
}
