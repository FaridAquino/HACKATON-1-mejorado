package com.example.hackaton_1_mejorada.Domain;

import com.example.hackaton_1_mejorada.Domain.Empresa.Empresa;
import jakarta.persistence.*;

@Entity
public class Restriccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoModelo;
    private int limiteUso;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    // Getters y setters...
}