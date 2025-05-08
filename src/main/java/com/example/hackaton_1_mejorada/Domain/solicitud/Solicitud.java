package com.example.hackaton_1_mejorada.Domain.solicitud;

import com.example.hackaton_1_mejorada.Domain.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String consulta;

    @Column(columnDefinition = "TEXT")
    private String respuesta;

    @Enumerated(EnumType.STRING)
    private solicitudEstado respuesta_estado; // Ejemplo: APROBADA, RECHAZADA

    @Enumerated(EnumType.STRING)
    private solicitudModelo modelo_usado; // Almacena el modelo usado (GPT, DeepSeek, Llama)

    private Integer tokens_necesarios = 1; // Número de tokens necesarios para la consulta

    @ManyToOne
    @JoinColumn(name = "consultante_id")
    @JsonBackReference
    private Usuario consultante;

}
