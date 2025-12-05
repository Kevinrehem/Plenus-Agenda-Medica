package com.jacaboy.plenus_agenda_medica.dto.Procedimento;

public record ProcedimentoGetDTO(
        Long id,
        String nome,
        String descricao,
        String categoria_procedimento,
        Integer tempo_estimado
) {
}
