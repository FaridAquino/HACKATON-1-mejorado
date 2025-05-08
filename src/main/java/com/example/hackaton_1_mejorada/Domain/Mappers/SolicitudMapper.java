package com.example.hackaton_1_mejorada.Domain.Mappers;

import com.example.hackaton_1_mejorada.Domain.DTO.requestSolicitudDTO;
import com.example.hackaton_1_mejorada.Domain.DTO.SolicitudResponseDTO;
import com.example.hackaton_1_mejorada.Domain.solicitud.Solicitud;
import com.example.hackaton_1_mejorada.Domain.usuario.Usuario;
import com.example.hackaton_1_mejorada.Domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SolicitudMapper {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Solicitud toEntity(requestSolicitudDTO dto, Long consultanteId) {
        if (dto == null) return null;

        Solicitud solicitud = new Solicitud();
        solicitud.setConsulta(dto.getConsulta());
        solicitud.setModelo_usado(dto.getModelo_usado());
        solicitud.setTokens_necesarios(1); // Valor por defecto

        if (consultanteId != null) {
            Usuario consultante = usuarioRepository.findById(consultanteId)
                .orElseThrow(() -> new RuntimeException("Usuario consultante no encontrado"));
            solicitud.setConsultante(consultante);
        }

        return solicitud;
    }

    public SolicitudResponseDTO toDTO(Solicitud solicitud) {
        if (solicitud == null) return null;

        SolicitudResponseDTO dto = new SolicitudResponseDTO();
        dto.setId(solicitud.getId());
        dto.setConsulta(solicitud.getConsulta());
        dto.setRespuesta(solicitud.getRespuesta());
        dto.setRespuesta_estado(solicitud.getRespuesta_estado());
        dto.setModelo_usado(solicitud.getModelo_usado());
        dto.setTokens_necesarios(solicitud.getTokens_necesarios());
        
        if (solicitud.getConsultante() != null) {
            dto.setConsultanteId(solicitud.getConsultante().getId());
        }

        return dto;
    }
}

