package com.example.hackaton_1_mejorada.Domain.Mappers;

import com.example.hackaton_1_mejorada.Domain.DTO.LimitesRequestDTO;
import com.example.hackaton_1_mejorada.Domain.DTO.LimitesResponseDTO;
import com.example.hackaton_1_mejorada.Domain.Empresa.Empresa;
import com.example.hackaton_1_mejorada.Domain.Empresa.EmpresaRepository;
import com.example.hackaton_1_mejorada.Domain.limites.Limites;
import com.example.hackaton_1_mejorada.Domain.limites.LimitesModelo;
import com.example.hackaton_1_mejorada.Domain.usuario.Usuario;
import com.example.hackaton_1_mejorada.Domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LimitesMapper {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    public Limites toEntity(LimitesRequestDTO dto) {
        if (dto == null) return null;

        Limites limites = new Limites();
        limites.setDescripcion(dto.getDescripcion());
        limites.setTokenSobrantes(dto.getTokenSobrantes());
        limites.setModelo(LimitesModelo.valueOf(dto.getModelo()));

        if (dto.getEmpresaId() != null) {
            Empresa empresa = empresaRepository.findById(dto.getEmpresaId())
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
            limites.setEmpresa(empresa);
        }

        if (dto.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            limites.setUsuario(usuario);
        }

        return limites;
    }

    public LimitesResponseDTO toDTO(Limites limites) {
        if (limites == null) return null;

        LimitesResponseDTO dto = new LimitesResponseDTO();
        dto.setId(limites.getId());
        dto.setDescripcion(limites.getDescripcion());
        dto.setTokenSobrantes(limites.getTokenSobrantes());
        dto.setModelo(limites.getModelo().name());
        
        if (limites.getEmpresa() != null) {
            dto.setEmpresaId(limites.getEmpresa().getId());
        }
        
        if (limites.getUsuario() != null) {
            dto.setUsuarioId(limites.getUsuario().getId());
        }

        return dto;
    }
}

