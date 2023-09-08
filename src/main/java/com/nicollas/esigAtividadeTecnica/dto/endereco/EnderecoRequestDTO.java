package com.nicollas.esigAtividadeTecnica.dto.endereco;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoRequestDTO {

    @NotNull(message = "O campo CEP não pode ser vazio!")
    private String cep;

    @NotNull(message = "O campo pais não pode ser vazio!")
    private String pais;

    @NotNull(message = "O campo cidade não pode ser vazio!")
    private String cidade;

    @NotNull(message = "O campo endereco não pode ser vazio!")
    private String endereco;


}
