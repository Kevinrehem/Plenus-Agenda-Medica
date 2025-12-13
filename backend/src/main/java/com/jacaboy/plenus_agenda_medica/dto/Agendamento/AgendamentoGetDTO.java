package com.jacaboy.plenus_agenda_medica.dto.Agendamento;

import com.jacaboy.plenus_agenda_medica.dto.Procedimento.ProcedimentoGetDTO;
import com.jacaboy.plenus_agenda_medica.dto.Usuario.Paciente.PacienteGetDTO;
import com.jacaboy.plenus_agenda_medica.dto.Usuario.Prestador.PrestadorGetDTO;

import java.util.List;

public record AgendamentoGetDTO(
        Long id,
        PrestadorGetDTO prestador,
        PacienteGetDTO paciente,
        String data_consulta,
        List<ProcedimentoGetDTO> procedimentos
) {
}
