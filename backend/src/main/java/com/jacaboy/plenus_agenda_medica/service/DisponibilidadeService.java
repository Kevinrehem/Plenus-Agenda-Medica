package com.jacaboy.plenus_agenda_medica.service;

import com.jacaboy.plenus_agenda_medica.dto.Disponibilidade.DisponibilidadeCreateDTO;
import com.jacaboy.plenus_agenda_medica.dto.Disponibilidade.DisponibilidadeGetDTO;
import com.jacaboy.plenus_agenda_medica.model.Disponibilidade;
import com.jacaboy.plenus_agenda_medica.repository.DisponibilidadeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DisponibilidadeService {

    private final DisponibilidadeRepository disponibilidadeRepository;

    public DisponibilidadeService(DisponibilidadeRepository disponibilidadeRepository) {
        this.disponibilidadeRepository = disponibilidadeRepository;
    }

    @Transactional
    public Long createDisponibilidade(DisponibilidadeCreateDTO request){
        if(request==null) return -1L;
        if(request.horario_inicio().length!=7 || request.horario_fim().length!=7) return -1L;
        LocalTime[] horarioInicio = new LocalTime[7];
        LocalTime[] horarioFim = new LocalTime[7];
        for(int i=0;i<7;i++){
            horarioInicio[i] = LocalTime.parse(request.horario_inicio()[i]);
            horarioFim[i] = LocalTime.parse(request.horario_fim()[i]);
        }
        Disponibilidade disponibilidade = Disponibilidade.builder()
                .horarioInicio(horarioInicio)
                .horarioFim(horarioFim)
                .build();
        disponibilidadeRepository.save(disponibilidade);

        return disponibilidade.getId();
    }

    @Transactional
    public List<DisponibilidadeGetDTO> findAllDisponibilidades(){
        List<Disponibilidade> disponibilidades = disponibilidadeRepository.findAll();
        if(disponibilidades.isEmpty()) return null;
        List<DisponibilidadeGetDTO> response = new ArrayList<>();
        disponibilidades.forEach(disponibilidade -> {
            response.add(convertToGetDTO(disponibilidade));
        });
        return response;
    }

    public DisponibilidadeGetDTO convertToGetDTO(Disponibilidade disponibilidade){
        return new DisponibilidadeGetDTO(
                disponibilidade.getId(),
                disponibilidade.getHorarioInicio(),
                disponibilidade.getHorarioFim()
        );
    }

}
