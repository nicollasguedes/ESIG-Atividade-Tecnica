package com.nicollas.esigAtividadeTecnica.dto.pessoaSalario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaSalarioRequestDTO {

    @NotNull(message = "O campo salario não pode ser vazio!")
    private BigDecimal salario;


}
