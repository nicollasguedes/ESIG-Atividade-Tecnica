package com.nicollas.esigAtividadeTecnica.dto.endereco;

import com.nicollas.esigAtividadeTecnica.model.EnderecoModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@Data
public class EnderecoResponseDTO {
    private BigInteger id;
    private String cep;
    private String pais;
    private String cidade;
    private String endereco;
    private BigInteger pessoaId;

    public static EnderecoResponseDTO convertToDto(EnderecoModel contato) {
        var enderecoResponseDTO = new EnderecoResponseDTO();

        enderecoResponseDTO.setId(contato.getId());
        enderecoResponseDTO.setCep(contato.getCep());
        enderecoResponseDTO.setEndereco(contato.getEndereco());
        enderecoResponseDTO.setPessoaId(contato.getPessoa().getId());

        return enderecoResponseDTO;
    }
}
