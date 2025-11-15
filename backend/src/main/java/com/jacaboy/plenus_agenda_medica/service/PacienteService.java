package com.jacaboy.plenus_agenda_medica.service;

import com.jacaboy.plenus_agenda_medica.dto.Usuario.Paciente.PacienteCreateDTO;
import com.jacaboy.plenus_agenda_medica.model.Paciente;
import com.jacaboy.plenus_agenda_medica.repository.PacienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class PacienteService {

    private PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }


    @Transactional
    public boolean createPaciente(PacienteCreateDTO request){
        if(request == null) return false;
        if(
                request.nome() == null || request.nome().isEmpty() ||
                        request.email() == null || request.email().isEmpty() ||
                        request.cpf() == null || request.cpf().isEmpty() ||
                        request.senha() == null || request.senha().isEmpty() ||
                        request.telefone() == null || request.telefone().isEmpty() ||
                        request.data_nascimento() == null || request.data_nascimento().isEmpty()
        ) return false;

        Paciente newPaciente = Paciente.builder()
                .nome(request.nome())
                .email(request.email())
                .cpf(request.cpf())
                .senha(request.senha())
                .dataNascimento(LocalDate.parse(request.data_nascimento()))
                .telefone(request.telefone())
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .ativo(true)
                .ehDevedor(false)
                .telefone2(request.telefone2())
                .build();


        pacienteRepository.save(newPaciente);
        return true;

    }
}
