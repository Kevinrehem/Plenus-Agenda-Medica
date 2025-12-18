package com.jacaboy.plenus_agenda_medica.dto.Procedimento;

public record ProcedimentoUpdateDTO(
        Long id,
        String nome,
        String descricao,
        String categoria_procedimento,
        Integer tempo_estimado
) {
}
