package com.example.hackaton_1_mejorada.Domain.Mappers;

import com.example.hackaton_1_mejorada.Domain.DTO.RestriccionesRequestDTO;
import com.example.hackaton_1_mejorada.Domain.DTO.RestriccionesResponseDTO;
import com.example.hackaton_1_mejorada.Domain.Empresa.Empresa;
import com.example.hackaton_1_mejorada.Domain.Empresa.EmpresaRepository;
import com.example.hackaton_1_mejorada.Domain.restricciones.Restricciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestriccionesMapper {

    @Autowired
    private EmpresaRepository empresaRepository;

    public Restricciones toEntity(RestriccionesRequestDTO dto) {
        if (dto == null) return null;

        Restricciones restricciones = new Restricciones();
        restricciones.setDescripcion(dto.getDescripcion());
        restricciones.setTipo(dto.getTipo());

        if (dto.getEmpresaId() != null) {
            Empresa empresa = empresaRepository.findById(dto.getEmpresaId())
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
            restricciones.setEmpresa(empresa);
        }

        return restricciones;
    }

    public RestriccionesResponseDTO toDTO(Restricciones restricciones) {
        if (restricciones == null) return null;

        RestriccionesResponseDTO dto = new RestriccionesResponseDTO();
        dto.setId(restricciones.getId());
        dto.setDescripcion(restricciones.getDescripcion());
        dto.setTipo(restricciones.getTipo());
        
        if (restricciones.getEmpresa() != null) {
            dto.setEmpresaId(restricciones.getEmpresa().getId());
        }

        return dto;
    }
}

