package com.jacaboy.plenus_agenda_medica.dto.Usuario.Paciente;

public record PacienteCreateDTO(String nome,
                                String email,
                                String telefone,
                                String telefone2,
                                String cpf,
                                String data_nascimento,
                                String senha) {
}
