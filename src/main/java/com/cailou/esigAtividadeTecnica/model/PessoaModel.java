package com.cailou.esigAtividadeTecnica.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "pessoa")
public class PessoaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    private UUID id;

    @Column(nullable = false, name = "Nome")
    private String nome;

    @Column(nullable = false, name = "Email")
    private String email;

    @Column(nullable = false, name = "CEP")
    private String CEP;

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
    private Date dataNascimento;

    @Column(nullable = false, name = "Cargo_ID")
    private String cargoId;

    @OneToOne
    @JoinColumn(nullable = false, name = "Usuario", referencedColumnName = "login")
    private UserModel login;

}
