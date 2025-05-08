package com.example.hackaton_1_mejorada.Domain.DTO;

import com.example.hackaton_1_mejorada.Domain.solicitud.solicitudEstado;
import com.example.hackaton_1_mejorada.Domain.solicitud.solicitudModelo;
import lombok.Data;

@Data
public class SolicitudResponseDTO {
    private Long id;
    private String consulta;
    private String respuesta;
    private solicitudEstado respuesta_estado;
    private solicitudModelo modelo_usado;
    private Integer tokens_necesarios;
    private Long consultanteId;
}

