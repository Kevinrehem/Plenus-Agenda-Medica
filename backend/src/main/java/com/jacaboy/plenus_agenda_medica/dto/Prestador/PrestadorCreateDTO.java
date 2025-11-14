package com.jacaboy.plenus_agenda_medica.dto.Prestador;

public record PrestadorCreateDTO(String nome,
                                 String email,
                                 String telefone,
                                 String telefone2,
                                 String cpf,
                                 String data_nascimento,
                                 String senha,
                                 String crbm) {
}
