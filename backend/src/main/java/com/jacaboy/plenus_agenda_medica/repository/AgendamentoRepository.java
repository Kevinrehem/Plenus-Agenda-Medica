package com.jacaboy.plenus_agenda_medica.repository;

import com.jacaboy.plenus_agenda_medica.dto.Agendamento.AgendamentoCreateDTO;
import com.jacaboy.plenus_agenda_medica.model.Agendamento;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento,Long> {


    @Query(nativeQuery = true, value = "SELECT EXISTS (" +
            "SELECT 1 " +
            "FROM agendamentos " +
            "WHERE (inicio_consulta < :fim AND fim_consulta > :inicio) " +
            "AND (prestador_id = :prestador_id OR paciente_id = :paciente_id) " +
            "AND id != :agendamento_id" +
            ");"
    )
    boolean conflitaAgendamento(@Param("inicio") LocalDateTime inicio,
                                @Param("fim")LocalDateTime fim,
                                @Param("prestador_id") Long prestadorId,
                                @Param("paciente_id") Long pacienteId,
                                @Param("agendamento_id") Long agendamentoId);


    default boolean temConflito(Agendamento request) {
        Long idVerificacao = (request.getId() != null) ? request.getId() : -1L;

        int weekDay = request.getInicioConsulta().toLocalDateTime().getDayOfWeek().getValue()-1;
        LocalTime consultaInicio = request.getInicioConsulta().toLocalDateTime().toLocalTime();
        LocalTime consultaFim = request.getFimConsulta().toLocalDateTime().toLocalTime();

        if(consultaInicio.isAfter(consultaFim)) return true;
        if(request.getPrestador().getDisponibilidade().getHorarioInicio() == request.getPrestador().getDisponibilidade().getHorarioFim()) return true;
        if(consultaInicio.isBefore(request.getPrestador().getDisponibilidade().getHorarioInicio()[weekDay])) return true;
        if(consultaFim.isAfter(request.getPrestador().getDisponibilidade().getHorarioFim()[weekDay])) return true;

        return conflitaAgendamento(
                request.getInicioConsulta().toLocalDateTime(),
                request.getFimConsulta().toLocalDateTime(),
                request.getPrestador().getId(),
                request.getPaciente().getId(),
                idVerificacao
        );
    }
}
