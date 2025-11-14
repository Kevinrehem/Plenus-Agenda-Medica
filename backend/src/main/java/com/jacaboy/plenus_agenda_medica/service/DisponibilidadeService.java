package com.jacaboy.plenus_agenda_medica.service;

import com.jacaboy.plenus_agenda_medica.dto.Disponibilidade.DisponibilidadeCreateDTO;
import com.jacaboy.plenus_agenda_medica.model.Disponibilidade;
import com.jacaboy.plenus_agenda_medica.repository.DisponibilidadeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalTime;

@Service
public class DisponibilidadeService {

    private final DisponibilidadeRepository disponibilidadeRepository;

    public DisponibilidadeService(DisponibilidadeRepository disponibilidadeRepository) {
        this.disponibilidadeRepository = disponibilidadeRepository;
    }

    @Transactional
    public boolean createDisponibilidade(DisponibilidadeCreateDTO request){
        if(request==null) return false;
        if(request.horario_inicio().length!=7 || request.horario_fim().length!=7) return false;
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
        return true;
    }

}
