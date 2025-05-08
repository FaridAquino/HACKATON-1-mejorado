package com.example.hackaton_1_mejorada.Domain.DTO;

import com.example.hackaton_1_mejorada.Domain.solicitud.solicitudModelo;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class requestSolicitudDTO {
    private String consulta;

    @Enumerated(EnumType.STRING)
    private solicitudModelo modelo_usado;
}
