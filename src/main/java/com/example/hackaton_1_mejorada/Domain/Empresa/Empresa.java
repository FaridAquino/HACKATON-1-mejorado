package com.example.hackaton_1_mejorada.Domain.Empresa;

import com.example.hackaton_1_mejorada.Domain.restricciones.Restricciones;
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
    private List<Restricciones> restricciones;

}
