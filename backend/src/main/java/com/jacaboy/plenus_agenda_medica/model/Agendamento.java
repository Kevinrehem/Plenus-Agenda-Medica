package com.jacaboy.plenus_agenda_medica.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Table(name="agendamentos")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable=false, unique=true, updatable=false)
    private Long id;

    @JoinColumn(name = "paciente_id",nullable=false, updatable=false)
    @ManyToOne(fetch=FetchType.LAZY)
    private Paciente paciente;

    @JoinColumn(name = "prestador_id", nullable=false, updatable=false)
    @ManyToOne(fetch=FetchType.LAZY)
    private Prestador prestador;

    @Column(nullable=false)
    private LocalDateTime dataConsulta;

    @Column(nullable=false)
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Procedimento> procedimentosSolicitados;

    @Column(nullable=false)
    private int tempoEstimado;

    @Column(nullable=false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @Column
    private LocalDateTime deletedAt;
}
