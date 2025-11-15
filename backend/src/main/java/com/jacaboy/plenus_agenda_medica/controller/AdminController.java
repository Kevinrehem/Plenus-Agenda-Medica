package com.jacaboy.plenus_agenda_medica.controller;

import com.jacaboy.plenus_agenda_medica.dto.Usuario.Admin.AdminCreateDTO;
import com.jacaboy.plenus_agenda_medica.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAdmin(@RequestBody AdminCreateDTO request){
        boolean response = adminService.createAdmin(request);
        if(response){
            return ResponseEntity.status(HttpStatus.CREATED).body("Admin " + request.nome() + " created successfully");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Admin " + request.nome() + " could not be created");
        }
    }
}
