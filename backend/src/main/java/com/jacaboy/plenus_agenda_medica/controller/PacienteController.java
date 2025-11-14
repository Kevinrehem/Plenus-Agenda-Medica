package com.jacaboy.plenus_agenda_medica.controller;

import com.jacaboy.plenus_agenda_medica.dto.Paciente.PacienteCreateDTO;
import com.jacaboy.plenus_agenda_medica.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/patient")
public class PacienteController {

    private UsuarioService usuarioService;

    public PacienteController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPaciente(@RequestBody PacienteCreateDTO request){
        boolean response = usuarioService.createPaciente(request);
        if(response){
            return ResponseEntity.status(HttpStatus.CREATED).body("Patient " + request.nome() + " created successfully");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Patient " + request.nome() + " could not be created");
        }
    }

}
