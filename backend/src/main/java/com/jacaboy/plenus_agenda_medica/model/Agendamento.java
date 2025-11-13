package com.jacaboy.plenus_agenda_medica.model;

import jakarta.persistence.*;
import lombok.*;

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

    @JoinColumn(nullable=false, updatable=false)
    @ManyToOne(fetch=FetchType.LAZY)
    private Paciente paciente;

}
