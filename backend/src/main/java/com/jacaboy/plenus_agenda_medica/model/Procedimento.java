package com.jacaboy.plenus_agenda_medica.model;

import com.jacaboy.plenus_agenda_medica.model.enums.CategoriaProcedimento;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "procedimentos")
public class Procedimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false,  unique=true)
    private String nome;

    @Column(nullable=false,  unique=true)
    private String descricao;

    @Column(nullable=false,  unique=true)
    private CategoriaProcedimento categoria;

    @Column(nullable = false)
    private Integer tempoEstimado;

    @Column
    @ManyToMany(mappedBy = "procedimentosDisponiveis",cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
    private List<Prestador> prestadoresAptos;

    @Column
    @ManyToMany(mappedBy = "procedimentosSolicitados",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Agendamento> agendamentos;

    @Column(nullable=false)
    private Timestamp createdAt;

    @Column
    private Timestamp updatedAt;

    @Column
    private Timestamp deletedAt;

}



