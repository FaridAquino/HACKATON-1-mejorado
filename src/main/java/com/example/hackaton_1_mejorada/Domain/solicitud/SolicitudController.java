package com.example.hackaton_1_mejorada.Domain.solicitud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {
    private final SolicitudService solicitudService;

    @Autowired
    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    // Obtener todas las solicitudes
    @GetMapping
    public List<Solicitud> getAllSolicitudes() {
        return solicitudService.findAll();
    }

    // Crear una nueva solicitud
    @PostMapping
    public Solicitud createSolicitud(@RequestBody Solicitud solicitud) {
        return solicitudService.save(solicitud);
    }

    // Eliminar una solicitud
    @DeleteMapping("/{id}")
    public void deleteSolicitud(@PathVariable Long id) {
        solicitudService.delete(id);
    }

    // Obtener historial de solicitudes de un usuario específico
    @GetMapping("/history")
    public List<Solicitud> getUserHistory(@RequestParam Long userId) {
        return solicitudService.findByUserId(userId);
    }
}
