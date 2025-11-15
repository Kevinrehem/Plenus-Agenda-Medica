package com.jacaboy.plenus_agenda_medica.controller;

import com.jacaboy.plenus_agenda_medica.dto.Disponibilidade.DisponibilidadeCreateDTO;
import com.jacaboy.plenus_agenda_medica.service.DisponibilidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/availability")
public class DisponibilidadeController {

    DisponibilidadeService disponibilidadeService;

    @Autowired
    public DisponibilidadeController(DisponibilidadeService disponibilidadeService) {
        this.disponibilidadeService = disponibilidadeService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createDisponibilidade(@RequestBody DisponibilidadeCreateDTO request){
        Long response = disponibilidadeService.createDisponibilidade(request);
        if(response<0L){
            return ResponseEntity.status(HttpStatus.CREATED).body("Horário cadastrado com sucesso");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao cadastrar horário");
    }

}
