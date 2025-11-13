package com.jacaboy.plenus_agenda_medica.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
}
