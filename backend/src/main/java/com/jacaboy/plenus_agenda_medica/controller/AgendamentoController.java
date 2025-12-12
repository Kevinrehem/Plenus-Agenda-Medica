package com.jacaboy.plenus_agenda_medica.controller;

import com.jacaboy.plenus_agenda_medica.dto.Agendamento.AgendamentoCreateDTO;
import com.jacaboy.plenus_agenda_medica.dto.Agendamento.AgendamentoGetDTO;
import com.jacaboy.plenus_agenda_medica.service.AgendamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/appointment")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAppointment(@RequestBody AgendamentoCreateDTO request) {
        boolean response = agendamentoService.createAgendamento(request);
        if (response) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Agendamento created successfully");
        }return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating Agendamento");
    }

    @GetMapping("/select-all")
    public ResponseEntity<List<AgendamentoGetDTO>> getAllAgendamentos() {
        List<AgendamentoGetDTO> response = agendamentoService.findAllAgendamentos();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
