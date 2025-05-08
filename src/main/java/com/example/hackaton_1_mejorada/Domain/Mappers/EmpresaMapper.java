package com.example.hackaton_1_mejorada.Domain.Mappers;

import com.example.hackaton_1_mejorada.Domain.DTO.EmpresaRequestDTO;
import com.example.hackaton_1_mejorada.Domain.DTO.EmpresaResponseDTO;
import com.example.hackaton_1_mejorada.Domain.Empresa.Empresa;
import com.example.hackaton_1_mejorada.Domain.Empresa.EmpresaRole;
import com.example.hackaton_1_mejorada.Domain.usuario.Usuario;
import com.example.hackaton_1_mejorada.Domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
public class EmpresaMapper {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Empresa toEntity(EmpresaRequestDTO dto) {
        if (dto == null) return null;
        
        Empresa empresa = new Empresa();
        empresa.setNombre(dto.getNombre());
        empresa.setRUC(dto.getRUC());
        empresa.setEstado(EmpresaRole.valueOf(dto.getEstado()));
        empresa.setGastado(dto.getGastado());
        empresa.setFecha_de_afiliacion(LocalDateTime.now());
        
        if (dto.getAdministradorId() != null) {
            Usuario administrador = usuarioRepository.findById(dto.getAdministradorId())
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));
            empresa.setAdministrador(administrador);
        }
        
        return empresa;
    }

    public EmpresaResponseDTO toDTO(Empresa empresa) {
        if (empresa == null) return null;
        
        EmpresaResponseDTO dto = new EmpresaResponseDTO();
        dto.setId(empresa.getId());
        dto.setNombre(empresa.getNombre());
        dto.setRUC(empresa.getRUC());
        dto.setFecha_de_afiliacion(empresa.getFecha_de_afiliacion());
        dto.setEstado(empresa.getEstado().name());
        dto.setGastado(empresa.getGastado());
        
        if (empresa.getAdministrador() != null) {
            dto.setAdministradorId(empresa.getAdministrador().getId());
        }
        
        if (empresa.getUsuarios() != null) {
            dto.setUsuariosIds(empresa.getUsuarios().stream()
                .map(Usuario::getId)
                .collect(Collectors.toList()));
        }
        
        if (empresa.getRestricciones() != null) {
            dto.setRestriccionesIds(empresa.getRestricciones().stream()
                .map(r -> r.getId())
                .collect(Collectors.toList()));
        }
        
        if (empresa.getSparky() != null) {
            dto.setSparkyId(empresa.getSparky().getId());
        }
        
        return dto;
    }
}

