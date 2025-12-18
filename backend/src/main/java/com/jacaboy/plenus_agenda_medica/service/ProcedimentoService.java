package com.jacaboy.plenus_agenda_medica.service;

import com.jacaboy.plenus_agenda_medica.dto.Procedimento.ProcedimentoCreateDTO;
import com.jacaboy.plenus_agenda_medica.dto.Procedimento.ProcedimentoGetDTO;
import com.jacaboy.plenus_agenda_medica.dto.Procedimento.ProcedimentoUpdateDTO;
import com.jacaboy.plenus_agenda_medica.model.Procedimento;
import com.jacaboy.plenus_agenda_medica.repository.PacienteRepository;
import com.jacaboy.plenus_agenda_medica.repository.ProcedimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Transactional
    public List<ProcedimentoGetDTO> findAllProcedimentos(){
        List<Procedimento> procedimentos = procedimentoRepository.findAll();
        if(procedimentos.isEmpty()) return null;
        List<ProcedimentoGetDTO> response = new ArrayList<>();
        procedimentos.forEach(procedimento -> {
            response.add(convertToGetDTO(procedimento));
        });
        return response;
    }

    @Transactional
    public ProcedimentoGetDTO findProcedimentoById(Long id){
        Procedimento procedimento =  procedimentoRepository.findById(id).orElse(null);
        if(procedimento == null) return null;
        return convertToGetDTO(procedimento);
    }

    @Transactional
    public boolean updateProcedimento(ProcedimentoUpdateDTO request){
        //TODO...
        return true;
    }

    public ProcedimentoGetDTO convertToGetDTO(Procedimento procedimento){
        return new ProcedimentoGetDTO(
                procedimento.getId(),
                procedimento.getNome(),
                procedimento.getDescricao(),
                procedimento.getCategoria().toString(),
                procedimento.getTempoEstimado()
        );
    }

}
