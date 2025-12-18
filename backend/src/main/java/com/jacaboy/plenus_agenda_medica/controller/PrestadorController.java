package com.jacaboy.plenus_agenda_medica.controller;

import com.jacaboy.plenus_agenda_medica.dto.Usuario.Prestador.PrestadorCreateDTO;
import com.jacaboy.plenus_agenda_medica.dto.Usuario.Prestador.PrestadorGetDTO;
import com.jacaboy.plenus_agenda_medica.dto.Usuario.Prestador.PrestadorUpdateDTO;
import com.jacaboy.plenus_agenda_medica.service.PrestadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/professional")
public class PrestadorController {

    private final PrestadorService prestadorService;

    @Autowired
    public PrestadorController(PrestadorService prestadorService) {
        this.prestadorService = prestadorService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPrestador(@RequestBody PrestadorCreateDTO request){
        boolean response =  prestadorService.createPrestador(request);
        if(response){
            return ResponseEntity.status(HttpStatus.CREATED).body("Prestador " + request.nome() +  " created successfully");
        }
        return ResponseEntity.badRequest().body("Prestador " + request.nome() + " couldn't be created");
    }

    @GetMapping("/select-all")
    public ResponseEntity<List<PrestadorGetDTO>> findAllPrestadores(){
        List<PrestadorGetDTO> response = prestadorService.findAllPrestadores();
        if(response.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/select/{id}")
    public ResponseEntity<PrestadorGetDTO> findPrestadorById(@PathVariable Long id){
        PrestadorGetDTO response = prestadorService.findPrestadorById(id);
        if(response==null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePrestador(@RequestBody PrestadorUpdateDTO request){
        boolean response =  prestadorService.updatePrestador(request);
        if(response){
            return ResponseEntity.status(HttpStatus.OK).body("Prestador updated successfully");
        } return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Prestador não pode ser atualizado");
    }

}
