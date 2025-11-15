package com.jacaboy.plenus_agenda_medica.service;

import com.jacaboy.plenus_agenda_medica.dto.Procedimento.ProcedimentoCreateDTO;
import com.jacaboy.plenus_agenda_medica.model.Procedimento;
import com.jacaboy.plenus_agenda_medica.repository.PacienteRepository;
import com.jacaboy.plenus_agenda_medica.repository.ProcedimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class ProcedimentoService {

    private ProcedimentoRepository procedimentoRepository;

    @Autowired
    public ProcedimentoService(ProcedimentoRepository procedimentoRepository) {
        this.procedimentoRepository = procedimentoRepository;
    }

    @Transactional
    public boolean createProcedimento(ProcedimentoCreateDTO request){
        if(request == null) return false;
        Procedimento procedimento = Procedimento.builder()
                .nome(request.nome())
                .descricao(request.descricao())
                .categoria(request.categoria())
                .tempoEstimado(request.tempo_estimado())
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        procedimentoRepository.save(procedimento);
        return true;
    }

}
