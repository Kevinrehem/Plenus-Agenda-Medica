package com.jacaboy.plenus_agenda_medica.dto.Disponibilidade;

import java.time.LocalTime;

public record DisponibilidadeGetDTO(
        Long id,
        LocalTime[] horario_inicio,
        LocalTime[] horario_fim
) {
}
