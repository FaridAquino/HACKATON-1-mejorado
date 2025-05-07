package com.example.hackaton_1_mejorada.Domain.sparky;

import com.example.hackaton_1_mejorada.Domain.Empresa.Empresa;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Sparky {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "sparky", cascade = CascadeType.ALL)
    private List<Empresa> empresas;

}
