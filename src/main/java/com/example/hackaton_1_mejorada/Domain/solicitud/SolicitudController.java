package com.example.hackaton_1_mejorada.Domain.solicitud;

import com.example.hackaton_1_mejorada.Domain.DTO.requestSolicitudDTO;
import com.example.hackaton_1_mejorada.Domain.DTO.SolicitudResponseDTO;
import com.example.hackaton_1_mejorada.Domain.Mappers.SolicitudMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/solicitudes")
@RequiredArgsConstructor
public class SolicitudController {
    private final SolicitudService solicitudService;
    private final SolicitudMapper solicitudMapper;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<SolicitudResponseDTO>> getAllSolicitudes() {
        List<Solicitud> solicitudes = solicitudService.findAll();
        List<SolicitudResponseDTO> solicitudesDTO = solicitudes.stream()
                .map(solicitudMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(solicitudesDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public ResponseEntity<SolicitudResponseDTO> createSolicitud(
            @RequestBody requestSolicitudDTO solicitudDTO,
            @RequestParam Long consultanteId) {
        Solicitud solicitud = solicitudMapper.toEntity(solicitudDTO, consultanteId);
        Solicitud nuevaSolicitud = solicitudService.save(solicitud);
        return new ResponseEntity<>(solicitudMapper.toDTO(nuevaSolicitud), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSolicitud(@PathVariable Long id) {
        solicitudService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/history")
    public ResponseEntity<List<SolicitudResponseDTO>> getUserHistory(@RequestParam Long userId) {
        List<Solicitud> solicitudes = solicitudService.findByUserId(userId);
        List<SolicitudResponseDTO> solicitudesDTO = solicitudes.stream()
                .map(solicitudMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(solicitudesDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<SolicitudResponseDTO> getSolicitud(@PathVariable Long id) {
        Solicitud solicitud = solicitudService.findById(id);
        return new ResponseEntity<>(solicitudMapper.toDTO(solicitud), HttpStatus.OK);
    }
}
