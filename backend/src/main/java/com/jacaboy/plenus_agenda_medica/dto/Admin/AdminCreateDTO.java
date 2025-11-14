package com.jacaboy.plenus_agenda_medica.dto.Admin;

public record AdminCreateDTO(String nome,
                             String email,
                             String telefone,
                             String telefone2,
                             String cpf,
                             String data_nascimento,
                             String senha
) {
}
