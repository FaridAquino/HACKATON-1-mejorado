package com.example.hackaton_1_mejorada.Domain.limites;

import com.example.hackaton_1_mejorada.Domain.DTO.LimitesRequestDTO;
import com.example.hackaton_1_mejorada.Domain.DTO.LimitesResponseDTO;
import com.example.hackaton_1_mejorada.Domain.Mappers.LimitesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/limites")
@RequiredArgsConstructor
public class LimitesController {
    private final LimitesService limitesService;
    private final LimitesMapper limitesMapper;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<LimitesResponseDTO>> getAllLimites() {
        List<Limites> limites = limitesService.findAll();
        List<LimitesResponseDTO> limitesDTO = limites.stream()
                .map(limitesMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(limitesDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<LimitesResponseDTO> getLimite(@PathVariable Long id) {
        Limites limite = limitesService.findById(id);
        return new ResponseEntity<>(limitesMapper.toDTO(limite), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<LimitesResponseDTO> createLimites(@RequestBody LimitesRequestDTO limitesDTO) {
        Limites limites = limitesMapper.toEntity(limitesDTO);
        Limites nuevoLimite = limitesService.save(limites);
        return new ResponseEntity<>(limitesMapper.toDTO(nuevoLimite), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<LimitesResponseDTO> updateLimites(
            @PathVariable Long id,
            @RequestBody LimitesRequestDTO limitesDTO) {
        Limites limites = limitesMapper.toEntity(limitesDTO);
        Limites limiteActualizado = limitesService.update(id, limites);
        return new ResponseEntity<>(limitesMapper.toDTO(limiteActualizado), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLimites(@PathVariable Long id) {
        limitesService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
