package com.jacaboy.plenus_agenda_medica.repository;

import com.jacaboy.plenus_agenda_medica.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento,Long> {
}
