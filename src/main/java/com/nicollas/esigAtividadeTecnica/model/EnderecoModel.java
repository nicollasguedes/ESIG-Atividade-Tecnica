package com.nicollas.esigAtividadeTecnica.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "endereco")
public class EnderecoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "ID", columnDefinition = "BIGINT")
    private BigInteger id;

    @Column(nullable = false, name = "CEP")
    private String cep;

    @Column(nullable = false, name = "Pais")
    private String pais;

    @Column(nullable = false, name = "Cidade")
    private String cidade;

    @Column(nullable = false, name = "endereco")
    private String endereco;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "pessoa_id", referencedColumnName = "id")
    private PessoaModel pessoa;

}
