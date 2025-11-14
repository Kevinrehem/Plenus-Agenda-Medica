package com.jacaboy.plenus_agenda_medica.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;

import java.util.List;

@EqualsAndHashCode(callSuper=true)
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "pacientes")
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Paciente extends Usuario {
    @Column(nullable = false)
    Boolean ehDevedor;

    @OneToMany(mappedBy = "paciente",  fetch = FetchType.LAZY)
    List<Agendamento> agendamentos;

}
