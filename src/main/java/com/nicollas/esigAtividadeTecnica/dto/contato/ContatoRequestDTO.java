package com.nicollas.esigAtividadeTecnica.dto.contato;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContatoRequestDTO {

    @NotNull(message = "O campo email não pode ser vazio!")
    @Email
    private String email;

    @NotNull(message = "O campo telefone não pode ser vazio!")
    private String telefone;



}
