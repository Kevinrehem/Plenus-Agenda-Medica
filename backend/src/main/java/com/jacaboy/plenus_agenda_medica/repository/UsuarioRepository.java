package com.jacaboy.plenus_agenda_medica.repository;

import com.jacaboy.plenus_agenda_medica.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
