package com.jacaboy.plenus_agenda_medica.service;

import com.jacaboy.plenus_agenda_medica.dto.Agendamento.AgendamentoCreateDTO;
import com.jacaboy.plenus_agenda_medica.dto.Agendamento.AgendamentoGetDTO;
import com.jacaboy.plenus_agenda_medica.dto.Procedimento.ProcedimentoGetDTO;
import com.jacaboy.plenus_agenda_medica.dto.Usuario.Paciente.PacienteGetDTO;
import com.jacaboy.plenus_agenda_medica.dto.Usuario.Prestador.PrestadorGetDTO;
import com.jacaboy.plenus_agenda_medica.model.Agendamento;
import com.jacaboy.plenus_agenda_medica.model.Paciente;
import com.jacaboy.plenus_agenda_medica.model.Prestador;
import com.jacaboy.plenus_agenda_medica.model.Procedimento;
import com.jacaboy.plenus_agenda_medica.repository.AgendamentoRepository;
import com.jacaboy.plenus_agenda_medica.repository.PacienteRepository;
import com.jacaboy.plenus_agenda_medica.repository.PrestadorRepository;
import com.jacaboy.plenus_agenda_medica.repository.ProcedimentoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class AgendamentoService {
    private final AgendamentoRepository agendamentoRepository;
    private final PrestadorRepository prestadorRepository;
    private final ProcedimentoRepository procedimentoRepository;
    private final PacienteRepository pacienteRepository;
    private final ProcedimentoService procedimentoService;
    private final PacienteService pacienteService;
    private final PrestadorService prestadorService;

    @Autowired
    public AgendamentoService(
            AgendamentoRepository agendamentoRepository,
            PrestadorRepository prestadorRepository,
            ProcedimentoRepository procedimentoRepository,
            PacienteRepository pacienteRepository,
            ProcedimentoService procedimentoService,
            PacienteService pacienteService,
            PrestadorService prestadorService
    ){
        this.agendamentoRepository = agendamentoRepository;
        this.prestadorRepository = prestadorRepository;
        this.procedimentoRepository = procedimentoRepository;
        this.pacienteRepository = pacienteRepository;
        this.procedimentoService = procedimentoService;
        this.pacienteService = pacienteService;
        this.prestadorService = prestadorService;
    }

    @Transactional
    public boolean createAgendamento(AgendamentoCreateDTO request){
        // Captura paciente, prestador e procedimentos no banco
        log.info("Iniciando criação de agendamento para paciente ID: {}", request.paciente_id()); // Logs estruturados
        Paciente paciente = pacienteRepository.findById(request.paciente_id()).orElse(null);
        Prestador prestador = prestadorRepository.findById(request.prestador_id()).orElse(null);
        List<Procedimento> procedimentos = procedimentoRepository.findAllById(Arrays.asList(request.procedimento_ids())).stream().toList();


        // Verificações de falha
        if(
                procedimentos.isEmpty()
                || prestador == null
                || paciente == null
        ) {
            log.warn("Falha ao criar agendamento: Dados inválidos ou não encontrados.");
            return false;
        }

        // Calcula tempo da consulta baseado nos procedimentos solicitados
        Integer tempoEstimado = 10;
        for(Procedimento procedimento : procedimentos){
            tempoEstimado += procedimento.getTempoEstimado();
        }
        log.debug("Tempo estimado calculado: {} minutos", tempoEstimado); // Use DEBUG para detalhes técnicos

        // Instancia Agendamento
        LocalDateTime inicioConsulta = LocalDateTime.parse(request.inicio_consulta().replace(' ', 'T'));
        Agendamento agendamento = Agendamento.builder()
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .paciente(paciente)
                .prestador(prestador)
                .procedimentosSolicitados(procedimentos)
                .tempoEstimado(tempoEstimado)
                .inicioConsulta(Timestamp.valueOf(inicioConsulta))
                .fimConsulta(Timestamp.valueOf(inicioConsulta.plusMinutes(tempoEstimado))) // inicio consulta + tempoEstimado minutos
                .build();
        System.out.println("Criou agendamento");
        if(agendamentoRepository.temConflito(agendamento)) {
            log.warn("Conflito de horário detectado para o prestador ID: {}", prestador.getId());
            return false;
        }

        agendamentoRepository.save(agendamento);
        log.info("Agendamento criado com sucesso. ID: {}", agendamento.getId());
        return true;
    }

    @Transactional
    public List<AgendamentoGetDTO> findAllAgendamentos(){
        List<Agendamento> agendamentos = agendamentoRepository.findAll().stream().toList();
        List<AgendamentoGetDTO> response = new ArrayList<>();

        for(Agendamento agendamento : agendamentos){
            response.add(convertToGetDTO(agendamento));
        }

        return response;
    }

    public AgendamentoGetDTO convertToGetDTO(Agendamento agendamento){
        PrestadorGetDTO prestador = prestadorService.convertToGetDTO(agendamento.getPrestador());
        PacienteGetDTO paciente = pacienteService.convertToGetDTO(agendamento.getPaciente());
        List<ProcedimentoGetDTO> procedimentos = new ArrayList<>();

        for(Procedimento procedimento: agendamento.getProcedimentosSolicitados()){
            procedimentos.add(procedimentoService.convertToGetDTO(procedimento));
        }

        return new AgendamentoGetDTO(
          prestador,
          paciente,
          agendamento.getInicioConsulta().toString(),
          procedimentos
        );
    }

}
