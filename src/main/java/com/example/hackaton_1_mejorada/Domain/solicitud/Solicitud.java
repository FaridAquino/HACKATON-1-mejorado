package com.example.hackaton_1_mejorada.Domain.solicitud;

import com.example.hackaton_1_mejorada.Domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private String estado; // Ejemplo: PENDIENTE, APROBADA, RECHAZADA
    private LocalDateTime fechaCreacion;
    private String modelo_usado;

    private Integer tokens_consumidos=0;


    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;




}
