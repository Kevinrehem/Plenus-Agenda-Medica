package com.jacaboy.plenus_agenda_medica.controller;

import com.jacaboy.plenus_agenda_medica.dto.Usuario.Paciente.PacienteCreateDTO;
import com.jacaboy.plenus_agenda_medica.service.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/patient")
public class PacienteController {

    private PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPaciente(@RequestBody PacienteCreateDTO request){
        boolean response = pacienteService.createPaciente(request);
        if(response){
            return ResponseEntity.status(HttpStatus.CREATED).body("Patient " + request.nome() + " created successfully");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Patient " + request.nome() + " could not be created");
        }
    }

}
