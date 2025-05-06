package com.example.hackathon.controller;

import com.example.hackathon.entity.Sparky;
import com.example.hackathon.service.SparkyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sparky")
public class SparkyController {
    private final SparkyService sparkyService;

    @Autowired
    public SparkyController(SparkyService sparkyService) {
        this.sparkyService = sparkyService;
    }

    @GetMapping
    public List<Sparky> getAllSparkies() {
        return sparkyService.findAll();
    }

    @PostMapping
    public Sparky createSparky(@RequestBody Sparky sparky) {
        return sparkyService.save(sparky);
    }

    @DeleteMapping("/{id}")
    public void deleteSparky(@PathVariable Long id) {
        sparkyService.delete(id);
    }
}
