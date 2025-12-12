package com.jacaboy.plenus_agenda_medica.dto.Usuario.Prestador;

import com.jacaboy.plenus_agenda_medica.dto.Disponibilidade.DisponibilidadeGetDTO;
import com.jacaboy.plenus_agenda_medica.dto.Procedimento.ProcedimentoGetDTO;

import java.util.List;

public record PrestadorGetDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String telefone2,
        String cpf,
        String data_nascimento,
        String createdAt,
        Boolean ativo,
        String crbm,
        DisponibilidadeGetDTO disponibilidade,
        List<ProcedimentoGetDTO> procedimentosDisponiveis
) {
}
