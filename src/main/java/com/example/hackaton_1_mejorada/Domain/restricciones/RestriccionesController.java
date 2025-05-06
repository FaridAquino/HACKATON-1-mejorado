package com.bitWiseComplexity.bigMeow.Domain.restricciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restricciones")
public class RestriccionesController {
    private final RestriccionesService restriccionesService;

    @Autowired
    public RestriccionesController(RestriccionesService restriccionesService) {
        this.restriccionesService = restriccionesService;
    }

    @GetMapping
    public List<Restricciones> getAllRestricciones() {
        return restriccionesService.findAll();
    }

    @PostMapping
    public Restricciones createRestricciones(@RequestBody Restricciones restricciones) {
        return restriccionesService.save(restricciones);
    }

    @DeleteMapping("/{id}")
    public void deleteRestricciones(@PathVariable Long id) {
        restriccionesService.delete(id);
    }
}
