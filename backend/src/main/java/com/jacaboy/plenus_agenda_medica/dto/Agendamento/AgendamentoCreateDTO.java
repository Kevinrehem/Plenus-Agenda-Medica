package com.jacaboy.plenus_agenda_medica.dto.Agendamento;

public record AgendamentoCreateDTO (
        Long paciente_id,
        Long prestador_id,
        String inicio_consulta,
        Long[] procedimento_ids
){
}
