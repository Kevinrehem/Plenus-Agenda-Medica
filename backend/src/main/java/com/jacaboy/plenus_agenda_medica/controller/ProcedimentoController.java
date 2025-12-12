package com.jacaboy.plenus_agenda_medica.controller;

import com.jacaboy.plenus_agenda_medica.dto.Procedimento.ProcedimentoCreateDTO;
import com.jacaboy.plenus_agenda_medica.dto.Procedimento.ProcedimentoGetDTO;
import com.jacaboy.plenus_agenda_medica.service.ProcedimentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/procedure")
public class ProcedimentoController {

    private final ProcedimentoService procedimentoService;

    public ProcedimentoController(ProcedimentoService procedimentoService) {
        this.procedimentoService = procedimentoService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProcedimento(@RequestBody ProcedimentoCreateDTO request){
        boolean response = procedimentoService.createProcedimento(request);
        if(response){
            return ResponseEntity.status(HttpStatus.CREATED).body("Procedimento " + request.nome() + " created successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Procedimento " + request.nome() + " could not be created");
    }

    @GetMapping("/select-all")
    public ResponseEntity<List<ProcedimentoGetDTO>> findAllProcedimentos(){
        List<ProcedimentoGetDTO> response =  procedimentoService.findAllProcedimentos();
        if(response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
