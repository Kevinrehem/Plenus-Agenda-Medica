package com.jacaboy.plenus_agenda_medica.dto.Usuario.Paciente;

public record PacienteGetDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String telefone2,
        String cpf,
        String data_nascimento,
        String createdAt,
        Boolean ativo,
        Boolean ehDevedor
) {
}
