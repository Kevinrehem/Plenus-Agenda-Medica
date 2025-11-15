package com.jacaboy.plenus_agenda_medica.controller;

import com.jacaboy.plenus_agenda_medica.dto.Prestador.PrestadorCreateDTO;
import com.jacaboy.plenus_agenda_medica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/professional")
public class PrestadorController {

    private final UsuarioService usuarioService;

    @Autowired
    public PrestadorController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPrestador(@RequestBody PrestadorCreateDTO request){
        boolean response =  usuarioService.createPrestador(request);
        if(response){
            return ResponseEntity.status(HttpStatus.CREATED).body("Prestador " + request.nome() +  " created successfully");
        }
        return ResponseEntity.badRequest().body("Prestador " + request.nome() + " couldn't be created");
    }

}
