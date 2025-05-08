package com.example.hackaton_1_mejorada.Domain.DTO;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class EmpresaResponseDTO {
    private Long id;
    private String nombre;
    private String RUC;
    private LocalDateTime fecha_de_afiliacion;
    private String estado;
    private Long administradorId;
    private Integer gastado;
    private List<Long> usuariosIds;
    private List<Long> restriccionesIds;
    private Long sparkyId;
}

