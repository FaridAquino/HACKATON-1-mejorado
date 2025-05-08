package com.example.hackaton_1_mejorada.Domain.DTO;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class EmpresaRequestDTO {
    private String nombre;
    private String RUC;
    private String estado;
    private Long administradorId;
    private Integer gastado;
    private Long sparkyId;
}

