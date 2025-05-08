package com.example.hackaton_1_mejorada.Domain.DTO;

import lombok.Data;

@Data
public class RestriccionesResponseDTO {
    private Long id;
    private String descripcion;
    private String tipo;
    private Long empresaId;
}

