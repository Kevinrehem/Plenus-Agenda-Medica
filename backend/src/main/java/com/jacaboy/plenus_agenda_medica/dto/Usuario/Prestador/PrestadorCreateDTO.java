package com.jacaboy.plenus_agenda_medica.dto.Usuario.Prestador;

import com.jacaboy.plenus_agenda_medica.dto.Disponibilidade.DisponibilidadeCreateDTO;

public record PrestadorCreateDTO(
        String nome,
        String email,
        String telefone,
        String telefone2,
        String cpf,
        String data_nascimento,
        String senha,
        String crbm,
        Long disponibilidade_id,
        DisponibilidadeCreateDTO disponibilidade,
        Long[] procedimento_ids
) {
}
