package com.jacaboy.plenus_agenda_medica.dto.Usuario;

public record UsuarioGetDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String telefone2,
        String cpf,
        String data_nascimento,
        String createdAt,
        Boolean ativo
) {
}
