package com.example.hackaton_1_mejorada.Domain.limites;

import com.example.hackaton_1_mejorada.Domain.Empresa.Empresa;

import com.example.hackaton_1_mejorada.Domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Limites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private double valorMaximo;
    private double valorMinimo;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;

}
