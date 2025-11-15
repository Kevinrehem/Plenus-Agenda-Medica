package com.jacaboy.plenus_agenda_medica.controller;

import com.jacaboy.plenus_agenda_medica.dto.Usuario.UsuarioGetDTO;
import com.jacaboy.plenus_agenda_medica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/user")
public class UsuarioController {

    private UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping("/select-all")
    public ResponseEntity<List<UsuarioGetDTO>> getAllUsers(){
        List<UsuarioGetDTO> response = usuarioService.getAllUsers();
        if(response == null) return ResponseEntity.badRequest().body(response);
        return ResponseEntity.ok(response);
    }

}
