package com.jacaboy.plenus_agenda_medica.repository;

import com.jacaboy.plenus_agenda_medica.model.Disponibilidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisponibilidadeRepository extends JpaRepository<Disponibilidade,Long> {
}
