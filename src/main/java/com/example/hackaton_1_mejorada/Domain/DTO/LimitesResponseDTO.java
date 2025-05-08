package com.example.hackaton_1_mejorada.Domain.DTO;

import lombok.Data;

@Data
public class LimitesResponseDTO {
    private Long id;
    private String descripcion;
    private Integer tokenSobrantes;
    private String modelo;
    private Long empresaId;
    private Long usuarioId;
}

