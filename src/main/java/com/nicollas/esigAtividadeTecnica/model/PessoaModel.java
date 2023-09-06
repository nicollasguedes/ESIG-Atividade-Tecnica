package com.nicollas.esigAtividadeTecnica.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Calendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "pessoa")
public class PessoaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "ID", columnDefinition = "BIGINT")
    private BigInteger id;

    @Column(nullable = false, name = "Nome")
    private String nome;

    @Column(nullable = false, name = "Email")
    private String email;

    @Column(nullable = false, name = "CEP")
    private String cep;

    @Column(nullable = false, name = "Pais")
    private String pais;

    @Column(nullable = false, name = "Cidade")
    private String cidade;

    @Column(nullable = false, name = "Endereco")
    private String endereco;

    @Column(nullable = false, name = "Telefone")
    private String telefone;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false, name = "Data_Nascimento")
    private Calendar dataNascimento;

    @Column(nullable = false, name = "Cargo_ID", columnDefinition = "INT")
    private int cargoId;

    @Column(name = "Usuario")
    private String login;

    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PessoaSalarioModel pessoaSalario = null;


}
