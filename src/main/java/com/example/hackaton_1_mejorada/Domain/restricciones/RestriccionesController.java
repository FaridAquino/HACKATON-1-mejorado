package com.example.hackaton_1_mejorada.Domain.restricciones;

import com.example.hackaton_1_mejorada.Domain.DTO.RestriccionesRequestDTO;
import com.example.hackaton_1_mejorada.Domain.DTO.RestriccionesResponseDTO;
import com.example.hackaton_1_mejorada.Domain.Mappers.RestriccionesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/company/restrictions")
@RequiredArgsConstructor
public class RestriccionesController {
    private final RestriccionesService restriccionesService;
    private final RestriccionesMapper restriccionesMapper;

    @PreAuthorize("hasRole('ROLE_COMPANY_ADMIN')")
    @GetMapping
    public ResponseEntity<List<RestriccionesResponseDTO>> getAllRestricciones(@RequestParam Long id) {
        List<Restricciones> restricciones = restriccionesService.findRestricciones(id);
        List<RestriccionesResponseDTO> restriccionesDTO = restricciones.stream()
                .map(restriccionesMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(restriccionesDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_COMPANY_ADMIN')")
    @PostMapping
    public ResponseEntity<RestriccionesResponseDTO> createRestricciones(
            @RequestBody RestriccionesRequestDTO restriccionesDTO,
            @RequestParam Long id_empresa) {
        Restricciones restricciones = restriccionesMapper.toEntity(restriccionesDTO);
        Restricciones nuevaRestriccion = restriccionesService.save(restricciones, id_empresa);
        return new ResponseEntity<>(restriccionesMapper.toDTO(nuevaRestriccion), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_COMPANY_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<RestriccionesResponseDTO> actualizarRestriccion(
            @PathVariable Long id,
            @RequestBody RestriccionesRequestDTO restriccionesDTO) {
        Restricciones restricciones = restriccionesMapper.toEntity(restriccionesDTO);
        Restricciones restriccionActualizada = restriccionesService.actualizar(id, restricciones);
        return new ResponseEntity<>(restriccionesMapper.toDTO(restriccionActualizada), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_COMPANY_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestricciones(@PathVariable Long id) {
        restriccionesService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
