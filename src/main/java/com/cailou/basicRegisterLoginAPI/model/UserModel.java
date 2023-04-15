package com.cailou.basicRegisterLoginAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Calendar;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    private UUID id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @NotBlank(message = "O campo senha não pode ser vazio!")
    private String name;

    @NotBlank(message = "O campo senha não pode ser vazio!")
    private String email;

    @NotBlank(message = "O campo senha não pode ser vazio!")
    private String cellphone;

    @Column(nullable = false, name = "active", columnDefinition = "boolean default false")
    private boolean active;

    @Column(nullable = false, name = "created_at")
    @CreatedDate
    private Calendar createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private Calendar updatedAt;

    @Column(name = "last_login_at")
    @LastModifiedDate
    private Calendar lastLoginDate;

    @ManyToOne()
    @JoinColumn(nullable = false, name = "role_id", referencedColumnName = "id")
    private RoleModel role;

}
