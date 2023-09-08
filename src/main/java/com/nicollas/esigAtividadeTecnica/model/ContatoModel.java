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
@Table(name = "contato")
public class ContatoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "ID", columnDefinition = "BIGINT")
    private BigInteger id;

    @Column(nullable = false, name = "Email")
    private String email;

    @Column(nullable = false, name = "Telefone")
    private String telefone;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "pessoa_id", referencedColumnName = "id")
    private PessoaModel pessoa;


}
