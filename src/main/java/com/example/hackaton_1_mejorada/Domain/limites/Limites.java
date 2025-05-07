package com.example.hackaton_1_mejorada.Domain.limites;

import com.example.hackaton_1_mejorada.Domain.Empresa.Empresa;
import com.example.hackaton_1_mejorada.Domain.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Limites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private Integer TokenSobrantes=5;

    @Enumerated(EnumType.STRING)
    private LimitesModelo modelo;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="usuario_id")
    private Usuario usuario;


}
