package com.example.hackaton_1_mejorada.Domain.restricciones;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestriccionesRepository extends JpaRepository<Restricciones, Long> {
}
