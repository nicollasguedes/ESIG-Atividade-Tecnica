package com.nicollas.esigAtividadeTecnica.dto.contato;

import com.nicollas.esigAtividadeTecnica.model.ContatoModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@Data
public class ContatoResponseDTO {
    private BigInteger id;
    private String email;
    private String telefone;
    private BigInteger pessoaId;

    public static ContatoResponseDTO convertToDto(ContatoModel contato) {
        var contatoResponseDTO = new ContatoResponseDTO();

        contatoResponseDTO.setId(contato.getId());
        contatoResponseDTO.setEmail(contato.getEmail());
        contatoResponseDTO.setTelefone(contato.getTelefone());
        contatoResponseDTO.setPessoaId(contato.getPessoa().getId());

        return contatoResponseDTO;
    }
}
