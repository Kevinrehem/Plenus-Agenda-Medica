package com.jacaboy.plenus_agenda_medica.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuarios")
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String telefone;

    @Column
    private String telefone2;

    @Column(nullable = false, unique = true, updatable = false)
    private String cpf;

    @Column(nullable = false, updatable = false)
    private Date dataNascimento;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @Column
    private LocalDateTime deletedAt;

    @Column
    private Boolean ativo;



    @Column
    private String senha;

    @Transient
    private boolean senhaAlterada;

    public void setSenha(String senha) {
        this.senha = senha;
        this.senhaAlterada = true;
    }

    @PrePersist
    public void prePersist(){
        encriptarSenha();
    }

    @PreUpdate
    public void preUpdate(){
        if(this.senhaAlterada){
            encriptarSenha();
        }
    }

    private void encriptarSenha(){
        if(this.senha != null && !this.senha.isEmpty()){
            this.senha = new BCryptPasswordEncoder().encode(this.senha);
            this.senhaAlterada = false;
        }
    }

}
