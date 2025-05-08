package com.example.hackaton_1_mejorada.Domain.solicitud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {

    // Método personalizado para encontrar solicitudes por ID del usuario
    List<Solicitud> findByConsultanteId(Long userId);
}
