package com.example.hackaton_1_mejorada.Domain.sparky;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SparkyService {
    private final SparkyRepository sparkyRepository;

    @Autowired
    public SparkyService(SparkyRepository sparkyRepository) {
        this.sparkyRepository = sparkyRepository;
    }

    public List<Sparky> findAll() {
        return sparkyRepository.findAll();
    }

    public Sparky save(Sparky sparky) {
        return sparkyRepository.save(sparky);
    }

    public void delete(Long id) {
        sparkyRepository.deleteById(id);
    }
}
