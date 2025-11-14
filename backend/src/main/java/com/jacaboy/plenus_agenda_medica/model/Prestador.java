package com.jacaboy.plenus_agenda_medica.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "prestadores")
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Prestador extends Usuario {

    @Column(nullable = false, name = "horario_trabalho")
    private String horario;

    @Column
    private String crbm;

    @OneToMany(mappedBy = "prestador", fetch = FetchType.LAZY)
    List<Agendamento> agendamentos;

    @Column
    @ManyToMany(cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
    private List<Procedimento> procedimentosDisponiveis;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Disponibilidade disponibilidade;

}
