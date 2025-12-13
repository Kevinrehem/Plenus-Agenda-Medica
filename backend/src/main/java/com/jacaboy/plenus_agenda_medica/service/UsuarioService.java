package com.jacaboy.plenus_agenda_medica.service;

import com.jacaboy.plenus_agenda_medica.dto.Usuario.UsuarioGetDTO;
import com.jacaboy.plenus_agenda_medica.model.Usuario;
import com.jacaboy.plenus_agenda_medica.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private final PacienteRepository pacienteRepository;
    private final PrestadorRepository prestadorRepository;
    private final AdminRepository adminRepository;
    private final UsuarioRepository usuarioRepository;
    private final DisponibilidadeRepository disponibilidadeRepository;
    private final DisponibilidadeService disponibilidadeService;
    private final ProcedimentoRepository procedimentoRepository;

    @Autowired
    public UsuarioService(
            PacienteRepository pacienteRepository,
            PrestadorRepository prestadorRepository,
            AdminRepository adminRepository,
            UsuarioRepository usuarioRepository,
            DisponibilidadeRepository disponibilidadeRepository,
            DisponibilidadeService disponibilidadeService,
            ProcedimentoRepository procedimentoRepository){
        this.pacienteRepository = pacienteRepository;
        this.prestadorRepository = prestadorRepository;
        this.adminRepository = adminRepository;
        this.usuarioRepository = usuarioRepository;
        this.disponibilidadeRepository = disponibilidadeRepository;
        this.disponibilidadeService = disponibilidadeService;
        this.procedimentoRepository = procedimentoRepository;
    }

    @Transactional
    public List<UsuarioGetDTO> findAllUsers(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioGetDTO> result = new ArrayList<>();
        if(usuarios.isEmpty()) return null;
        for(Usuario usuario : usuarios){
            result.add(convertToGetDTO(usuario));
        }
        return result;
    }

    @Transactional
    public UsuarioGetDTO findUserById(Long id){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if(usuario == null) return null;
        return convertToGetDTO(usuario);
    }

    private UsuarioGetDTO convertToGetDTO(Usuario usuario){
        UsuarioGetDTO result = new UsuarioGetDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getTelefone2(),
                usuario.getCpf(),
                usuario.getDataNascimento().toString(),
                usuario.getCreatedAt().toString(),
                usuario.getAtivo()
        );
        return result;
    }





}
