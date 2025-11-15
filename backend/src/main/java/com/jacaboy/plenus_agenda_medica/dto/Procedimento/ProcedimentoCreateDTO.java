package com.jacaboy.plenus_agenda_medica.dto.Procedimento;

import com.jacaboy.plenus_agenda_medica.model.enums.CategoriaProcedimento;

public record ProcedimentoCreateDTO(
        String nome,
        String descricao,
        CategoriaProcedimento categoria,
        Integer tempo_estimado
) {
}
