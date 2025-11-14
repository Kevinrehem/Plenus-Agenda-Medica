package com.jacaboy.plenus_agenda_medica.service;

import com.jacaboy.plenus_agenda_medica.dto.Admin.AdminCreateDTO;
import com.jacaboy.plenus_agenda_medica.model.Admin;
import com.jacaboy.plenus_agenda_medica.repository.AdminRepository;
import com.jacaboy.plenus_agenda_medica.repository.PacienteRepository;
import com.jacaboy.plenus_agenda_medica.repository.PrestadorRepository;
import com.jacaboy.plenus_agenda_medica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class UsuarioService {

    private final PacienteRepository pacienteRepository;
    private final PrestadorRepository prestadorRepository;
    private final AdminRepository adminRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(
            PacienteRepository pacienteRepository,
            PrestadorRepository prestadorRepository,
            AdminRepository adminRepository,
            UsuarioRepository usuarioRepository
    ){
        this.pacienteRepository = pacienteRepository;
        this.prestadorRepository = prestadorRepository;
        this.adminRepository = adminRepository;
        this.usuarioRepository = usuarioRepository;
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
                .build();

        if(request.telefone2() != null && !request.telefone2().isEmpty()){
            newAdmin.setTelefone(request.telefone2());
        }

        adminRepository.save(newAdmin);
        return true;

    }

}
