package com.example.hackaton_1_mejorada.Domain.restricciones;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company/restrictions")
@RequiredArgsConstructor
public class RestriccionesController {
    private final RestriccionesService restriccionesService;

    @PreAuthorize("hasRole('ROLE_COMPANY_ADMIN')")
    @GetMapping
    public ResponseEntity<List<Restricciones>> getAllRestricciones(@RequestParam Long id) {
        List<Restricciones> restricciones=restriccionesService.findRestricciones(id);
        return new ResponseEntity<>(restricciones, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_COMPANY_ADMIN')")
    @PostMapping
    public ResponseEntity<Restricciones> createRestricciones(@RequestBody Restricciones restricciones, @RequestParam Long id_empresa) {
        Restricciones restricciones1=restriccionesService.save(restricciones,id_empresa);
        return new ResponseEntity<>(restricciones1,HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_COMPANY_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Restricciones> actualizarRestriccion(@PathVariable Long id, @RequestBody Restricciones restricciones){
        Restricciones restricciones1=restriccionesService.actualizar(id, restricciones);
        return new ResponseEntity<>(restricciones1,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_COMPANY_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestricciones(@PathVariable Long id) {
        restriccionesService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
