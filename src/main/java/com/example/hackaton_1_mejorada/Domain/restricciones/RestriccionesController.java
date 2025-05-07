package com.example.hackaton_1_mejorada.Domain.restricciones;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company/restrictions")
@RequiredArgsConstructor
public class RestriccionesController {
    private final RestriccionesService restriccionesService;

    @GetMapping
    public ResponseEntity<List<Restricciones>> getAllRestricciones() {
        List<Restricciones> restricciones=restriccionesService.findAll();
        return new ResponseEntity<>(restricciones, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Restricciones> createRestricciones(@RequestBody Restricciones restricciones, @RequestParam Long id) {
        Restricciones restricciones1=restriccionesService.save(restricciones,id);
        return new ResponseEntity<>(restricciones1,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestricciones(@PathVariable Long id) {

        restriccionesService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
