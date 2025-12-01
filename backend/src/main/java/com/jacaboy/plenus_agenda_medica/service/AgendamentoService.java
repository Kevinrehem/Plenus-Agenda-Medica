package com.jacaboy.plenus_agenda_medica.service;

import com.jacaboy.plenus_agenda_medica.dto.Agendamento.AgendamentoCreateDTO;
import com.jacaboy.plenus_agenda_medica.model.Agendamento;
import com.jacaboy.plenus_agenda_medica.model.Paciente;
import com.jacaboy.plenus_agenda_medica.model.Prestador;
import com.jacaboy.plenus_agenda_medica.model.Procedimento;
import com.jacaboy.plenus_agenda_medica.repository.AgendamentoRepository;
import com.jacaboy.plenus_agenda_medica.repository.PacienteRepository;
import com.jacaboy.plenus_agenda_medica.repository.PrestadorRepository;
import com.jacaboy.plenus_agenda_medica.repository.ProcedimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class AgendamentoService {
    private final AgendamentoRepository agendamentoRepository;
    private final PrestadorRepository prestadorRepository;
    private final ProcedimentoRepository procedimentoRepository;
    private final PacienteRepository pacienteRepository;

    @Autowired
    public AgendamentoService(
            AgendamentoRepository agendamentoRepository,
            PrestadorRepository prestadorRepository,
            ProcedimentoRepository procedimentoRepository,
            PacienteRepository pacienteRepository
    ){
        this.agendamentoRepository = agendamentoRepository;
        this.prestadorRepository = prestadorRepository;
        this.procedimentoRepository = procedimentoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    @Transactional
    public boolean createAgendamento(AgendamentoCreateDTO request){
        // Captura paciente, prestador e procedimentos no banco
        Paciente paciente = pacienteRepository.findById(request.paciente_id()).orElse(null);
        Prestador prestador = prestadorRepository.findById(request.prestador_id()).orElse(null);
        List<Procedimento> procedimentos = procedimentoRepository.findAllById(Arrays.asList(request.procedimento_ids())).stream().toList();

        // Verificações de falha
        if(
                procedimentos.isEmpty()
                || prestador == null
                || paciente == null
        ) return false;

        // Calcula tempo da consulta baseado nos procedimentos solicitados
        Integer tempoEstimado = 10;
        for(Procedimento procedimento : procedimentos){
            tempoEstimado += procedimento.getTempoEstimado();
        }

        // Instancia Agendamento
        LocalDateTime inicioConsulta = LocalDateTime.parse(request.inicio_consulta());
        Agendamento agendamento = Agendamento.builder()
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .paciente(paciente)
                .prestador(prestador)
                .procedimentosSolicitados(procedimentos)
                .tempoEstimado(tempoEstimado)
                .inicioConsulta(Timestamp.valueOf(inicioConsulta))
                .fimConsulta(Timestamp.valueOf(inicioConsulta.plusMinutes(tempoEstimado))) // inicio consulta + tempoEstimado minutos
                .build();

        if(agendamentoRepository.temConflito(agendamento)) return false ;
        agendamentoRepository.save(agendamento);
        return true;
    }

}
