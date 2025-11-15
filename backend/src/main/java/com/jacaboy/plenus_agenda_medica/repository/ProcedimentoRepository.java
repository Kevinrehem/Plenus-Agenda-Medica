package com.jacaboy.plenus_agenda_medica.repository;

import com.jacaboy.plenus_agenda_medica.model.Procedimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcedimentoRepository extends JpaRepository<Procedimento,Long> {
}
