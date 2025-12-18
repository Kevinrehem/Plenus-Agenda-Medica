package com.jacaboy.plenus_agenda_medica.service;

import com.jacaboy.plenus_agenda_medica.dto.Disponibilidade.DisponibilidadeGetDTO;
import com.jacaboy.plenus_agenda_medica.dto.Procedimento.ProcedimentoGetDTO;
import com.jacaboy.plenus_agenda_medica.dto.Usuario.Prestador.PrestadorCreateDTO;
import com.jacaboy.plenus_agenda_medica.dto.Usuario.Prestador.PrestadorGetDTO;
import com.jacaboy.plenus_agenda_medica.dto.Usuario.Prestador.PrestadorUpdateDTO;
import com.jacaboy.plenus_agenda_medica.model.Disponibilidade;
import com.jacaboy.plenus_agenda_medica.model.Prestador;
import com.jacaboy.plenus_agenda_medica.model.Procedimento;
import com.jacaboy.plenus_agenda_medica.repository.DisponibilidadeRepository;
import com.jacaboy.plenus_agenda_medica.repository.PrestadorRepository;
import com.jacaboy.plenus_agenda_medica.repository.ProcedimentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PrestadorService {

    private final PrestadorRepository prestadorRepository;
    private final DisponibilidadeService disponibilidadeService;
    private final DisponibilidadeRepository disponibilidadeRepository;
    private final ProcedimentoRepository procedimentoRepository;
    private final ProcedimentoService procedimentoService;

    public PrestadorService(
            PrestadorRepository prestadorRepository,
            DisponibilidadeRepository disponibilidadeRepository,
            ProcedimentoRepository procedimentoRepository,
            DisponibilidadeService disponibilidadeService,
            ProcedimentoService procedimentoService
    ) {
        this.prestadorRepository = prestadorRepository;
        this.disponibilidadeService = disponibilidadeService;
        this.procedimentoRepository = procedimentoRepository;
        this.disponibilidadeRepository = disponibilidadeRepository;
        this.procedimentoService = procedimentoService;
    }

    @Transactional
    public boolean createPrestador(PrestadorCreateDTO request){
        // Se campos obrigatórios forem null ou houver mais de uma disponibilidade, retorna falso
        if(request == null) return false;
        if(
                request.nome() == null || request.nome().isEmpty() ||
                        request.email() == null || request.email().isEmpty() ||
                        request.cpf() == null || request.cpf().isEmpty() ||
                        request.senha() == null || request.senha().isEmpty() ||
                        request.telefone() == null || request.telefone().isEmpty() ||
                        request.data_nascimento() == null || request.data_nascimento().isEmpty()
        ) return false;
        if(request.disponibilidade() == null && request.disponibilidade_id() == null) return false;
        if(request.disponibilidade() != null && request.disponibilidade_id() != null) return false;

        // Instancia objeto prestador
        Prestador prestador = Prestador.builder()
                .nome(request.nome())
                .email(request.email())
                .cpf(request.cpf())
                .senha(request.senha())
                .dataNascimento(LocalDate.parse(request.data_nascimento()))
                .telefone(request.telefone())
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .ativo(true)
                .crbm(request.crbm())
                .telefone2(request.telefone2())
                .procedimentosDisponiveis(procedimentoRepository.findAllById(Arrays.asList(request.procedimento_ids())))
                .build();

        // Set da disponibilidade do profissional
        if(request.disponibilidade_id() != null){
            if(!disponibilidadeRepository.existsById(request.disponibilidade_id())) return false;
            prestador.setDisponibilidade(disponibilidadeRepository.findById(request.disponibilidade_id()).orElse(null));
        }
        if(request.disponibilidade() != null){
            Long disponibilidadeId = disponibilidadeService.createDisponibilidade(request.disponibilidade());
            if(disponibilidadeId == -1L) return false;
            prestador.setDisponibilidade(disponibilidadeRepository.findById(disponibilidadeId).orElse(null));
        }

        prestadorRepository.save(prestador);

        // Se chegou aqui é porque deu certo, retorna true
        return true;
    }

    @Transactional
    public List<PrestadorGetDTO> findAllPrestadores(){
        List<Prestador> prestadores = prestadorRepository.findAll().stream().toList();
        if(prestadores.isEmpty()) return null;
        List<PrestadorGetDTO> result = new ArrayList<>();
        for(Prestador prestador : prestadores){
            result.add(convertToGetDTO(prestador));
        }
        return result;
    }

    @Transactional
    public PrestadorGetDTO findPrestadorById(Long id){
        Prestador prestador = prestadorRepository.findById(id).orElse(null);
        if(prestador == null) return null;
        return convertToGetDTO(prestador);
    }

    @Transactional
    public boolean updatePrestador(PrestadorUpdateDTO request){
        Prestador prestador = prestadorRepository.findById(request.id()).orElse(null);
        if(prestador == null) return false;
        if(request.nome() != null && !request.nome().isEmpty()) prestador.setNome(request.nome());
        if(request.email() != null && !request.email().isEmpty()) prestador.setEmail(request.email());
        if(request.cpf() != null && !request.cpf().isEmpty()) prestador.setCpf(request.cpf());
        if(request.senha() != null && !request.senha().isEmpty()) prestador.setSenha(request.senha());
        if(request.telefone() != null && !request.telefone().isEmpty()) prestador.setTelefone(request.telefone());
        if(request.telefone2() != null && !request.telefone2().isEmpty())  prestador.setTelefone2(request.telefone2());
        if(request.data_nascimento() != null && !request.data_nascimento().isEmpty()) prestador.setDataNascimento(LocalDate.parse(request.data_nascimento()));
        if(request.crbm() != null && !request.crbm().isEmpty()) prestador.setCrbm(request.crbm());
        if(request.disponibilidade_id() != null){
            prestador.setDisponibilidade(disponibilidadeRepository.findById(request.disponibilidade_id()).orElse(null));
            if (prestador.getDisponibilidade() == null) return false;
        }
        if(request.disponibilidade() != null){
            Long id = disponibilidadeService.createDisponibilidade(request.disponibilidade());
            Disponibilidade disponibilidade = disponibilidadeRepository.findById(id).orElse(null);
        }
        if(request.procedimento_ids() != null) {
            List<Procedimento> procedimentos = new ArrayList<>();
            for (int i = 0; i < request.procedimento_ids().length; i++) {
                if (procedimentoRepository.existsById(request.procedimento_ids()[i])) {
                    procedimentos.add(procedimentoRepository.findById(request.procedimento_ids()[i]).orElse(null));
                } else return false;
            }
            if (!procedimentos.isEmpty()) prestador.setProcedimentosDisponiveis(procedimentos);
        }
        prestadorRepository.save(prestador);
        return true;
    }

    public PrestadorGetDTO convertToGetDTO(Prestador prestador){
        List<ProcedimentoGetDTO> procedimentosDisponiveis = new ArrayList<>();
        for(Procedimento procedimento : prestador.getProcedimentosDisponiveis()){
            procedimentosDisponiveis.add(procedimentoService.convertToGetDTO(procedimento));
        }


        PrestadorGetDTO response = new PrestadorGetDTO(
                prestador.getId(),
                prestador.getNome(),
                prestador.getEmail(),
                prestador.getTelefone(),
                prestador.getTelefone2(),
                prestador.getCpf(),
                prestador.getDataNascimento().toString(),
                prestador.getCreatedAt().toString(),
                prestador.getAtivo(),
                prestador.getCrbm(),
                disponibilidadeService.convertToGetDTO(prestador.getDisponibilidade()),
                procedimentosDisponiveis
        );
        return response;
    }

}
