package com.jacaboy.plenus_agenda_medica.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper=true)
@Entity
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Table(name = "administradores")
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Admin extends Usuario {
}
