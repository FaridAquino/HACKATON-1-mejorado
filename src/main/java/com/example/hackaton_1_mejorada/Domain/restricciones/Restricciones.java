package com.example.hackaton_1_mejorada.Domain.restricciones;

import com.example.hackaton_1_mejorada.Domain.Empresa.Empresa;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Restricciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private String tipo; // Ejemplo: TIPO1, TIPO2, etc.

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    @JsonBackReference
    private Empresa empresa;
}
