package com.jacaboy.plenus_agenda_medica.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;


@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "disponibilidade")
public class Disponibilidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(nullable = false, unique = true)
    private LocalTime[] horarioInicio =  new LocalTime[7];

    @Column(nullable = false, unique = true)
    private LocalTime[] horarioFim =  new LocalTime[7];

    @OneToMany(mappedBy = "disponibilidade", fetch = FetchType.LAZY)
    private List<Prestador> prestador;


}
