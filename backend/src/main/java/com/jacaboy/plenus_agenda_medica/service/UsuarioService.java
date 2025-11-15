package com.jacaboy.plenus_agenda_medica.service;

import com.jacaboy.plenus_agenda_medica.dto.Admin.AdminCreateDTO;
import com.jacaboy.plenus_agenda_medica.dto.Paciente.PacienteCreateDTO;
import com.jacaboy.plenus_agenda_medica.dto.Prestador.PrestadorCreateDTO;
import com.jacaboy.plenus_agenda_medica.model.Admin;
import com.jacaboy.plenus_agenda_medica.model.Disponibilidade;
import com.jacaboy.plenus_agenda_medica.model.Paciente;
import com.jacaboy.plenus_agenda_medica.model.Prestador;
import com.jacaboy.plenus_agenda_medica.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@Service
public class UsuarioService {

    private final PacienteRepository pacienteRepository;
    private final PrestadorRepository prestadorRepository;
    private final AdminRepository adminRepository;
    private final UsuarioRepository usuarioRepository;
    private final DisponibilidadeRepository disponibilidadeRepository;
    private final DisponibilidadeService disponibilidadeService;
    private final ProcedimentoRepository procedimentoRepository;

    @Autowired
    public UsuarioService(
            PacienteRepository pacienteRepository,
            PrestadorRepository prestadorRepository,
            AdminRepository adminRepository,
            UsuarioRepository usuarioRepository,
            DisponibilidadeRepository disponibilidadeRepository,
            DisponibilidadeService disponibilidadeService,
            ProcedimentoRepository procedimentoRepository){
        this.pacienteRepository = pacienteRepository;
        this.prestadorRepository = prestadorRepository;
        this.adminRepository = adminRepository;
        this.usuarioRepository = usuarioRepository;
        this.disponibilidadeRepository = disponibilidadeRepository;
        this.disponibilidadeService = disponibilidadeService;
        this.procedimentoRepository = procedimentoRepository;
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

    @Transactional
    public boolean createPrestador(PrestadorCreateDTO request){
        // Se campos obrigatórios forem null ou houver mais de uma disponibilidade, retorna falso
        if(request == null) return false;
        if(
                request.nome() == null || request.nome().isEmpty() ||
                        request.email() == null || request.email().isEmpty() ||
                        request.cpf() == null || request.cpf().isEmpty() ||
                        request.senha() == null || request.senha().isEmpty() ||
                        request.telefone() == null || request.telefone().isEmpty() ||
                        request.data_nascimento() == null || request.data_nascimento().isEmpty()
        ) return false;
        if(request.disponibilidade() == null && request.disponibilidade_id() == null) return false;
        if(request.disponibilidade() != null && request.disponibilidade_id() != null) return false;

        // Instancia objeto prestador
        Prestador prestador = Prestador.builder()
                .nome(request.nome())
                .email(request.email())
                .cpf(request.cpf())
                .senha(request.senha())
                .dataNascimento(LocalDate.parse(request.data_nascimento()))
                .telefone(request.telefone())
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .ativo(true)
                .crbm(request.crbm())
                .telefone2(request.telefone2())
                .procedimentosDisponiveis(procedimentoRepository.findAllById(Arrays.asList(request.procedimento_ids())))
                .build();

        // Set da disponibilidade do profissional
        if(request.disponibilidade_id() != null){
            if(!disponibilidadeRepository.existsById(request.disponibilidade_id())) return false;
            prestador.setDisponibilidade(disponibilidadeRepository.findById(request.disponibilidade_id()).orElse(null));
        }
        if(request.disponibilidade() != null){
            Long disponibilidadeId = disponibilidadeService.createDisponibilidade(request.disponibilidade());
            if(disponibilidadeId == -1L) return false;
            prestador.setDisponibilidade(disponibilidadeRepository.findById(disponibilidadeId).orElse(null));
        }

        prestadorRepository.save(prestador);

        // Se chegou aqui é porque deu certo, retorna true
        return true;
    }

}
