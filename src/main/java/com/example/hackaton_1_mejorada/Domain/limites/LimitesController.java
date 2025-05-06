package com.bitWiseComplexity.bigMeow.Domain.limites;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/limites")
public class LimitesController {
    private final LimitesService limitesService;

    @Autowired
    public LimitesController(LimitesService limitesService) {
        this.limitesService = limitesService;
    }

    @GetMapping
    public List<Limites> getAllLimites() {
        return limitesService.findAll();
    }

    @PostMapping
    public Limites createLimites(@RequestBody Limites limites) {
        return limitesService.save(limites);
    }

    @DeleteMapping("/{id}")
    public void deleteLimites(@PathVariable Long id) {
        limitesService.delete(id);
    }
}
