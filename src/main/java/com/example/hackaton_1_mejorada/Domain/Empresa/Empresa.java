package com.example.hackaton_1_mejorada.Domain.Empresa;

import com.example.hackaton_1_mejorada.Domain.restricciones.Restricciones;
import com.example.hackaton_1_mejorada.Domain.sparky.Sparky;
import com.example.hackaton_1_mejorada.Domain.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String RUC;

    private LocalDateTime fecha_de_afiliacion;

    @Enumerated(EnumType.STRING)
    private EmpresaRole estado;

    private String  name_administrador;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Restricciones> restricciones;

    @OneToMany(mappedBy = "empresa",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Usuario> usuarios;

    @ManyToOne
    @JoinColumn(name = "sparky_id")
    private Sparky sparky;

}
