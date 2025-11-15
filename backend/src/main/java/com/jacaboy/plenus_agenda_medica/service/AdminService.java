package com.jacaboy.plenus_agenda_medica.service;

import com.jacaboy.plenus_agenda_medica.dto.Usuario.Admin.AdminCreateDTO;
import com.jacaboy.plenus_agenda_medica.model.Admin;
import com.jacaboy.plenus_agenda_medica.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class AdminService {
    private AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Transactional
    public boolean createAdmin(AdminCreateDTO request){
        if(request == null) return false;
        if(
                request.nome() == null || request.nome().isEmpty() ||
                        request.email() == null || request.email().isEmpty() ||
                        request.cpf() == null || request.cpf().isEmpty() ||
                        request.senha() == null || request.senha().isEmpty() ||
                        request.telefone() == null || request.telefone().isEmpty() ||
                        request.data_nascimento() == null || request.data_nascimento().isEmpty()
        ) return false;

        Admin newAdmin = Admin.builder()
                .nome(request.nome())
                .email(request.email())
                .cpf(request.cpf())
                .senha(request.senha())
                .dataNascimento(LocalDate.parse(request.data_nascimento()))
                .telefone(request.telefone())
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .ativo(true)
                .telefone2(request.telefone2())
                .build();



        adminRepository.save(newAdmin);
        return true;

    }

}
