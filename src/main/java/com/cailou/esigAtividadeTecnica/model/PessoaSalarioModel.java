package com.cailou.esigAtividadeTecnica.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "pessoa_salario")
public class PessoaSalarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id", columnDefinition = "BIGINT")
    private BigInteger id;

    @Column(nullable = false, name = "nome")
    private String nome;

    @Column(nullable = false, name = "salario")
    private BigDecimal salario;

    @OneToOne
    @JoinColumn(nullable = false, name = "pessoa_id", referencedColumnName = "id")
    private PessoaModel pessoa;

}