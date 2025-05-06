package com.bitWiseComplexity.bigMeow.Domain.restricciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestriccionesService {
    private final RestriccionesRepository restriccionesRepository;

    @Autowired
    public RestriccionesService(RestriccionesRepository restriccionesRepository) {
        this.restriccionesRepository = restriccionesRepository;
    }

    public List<Restricciones> findAll() {
        return restriccionesRepository.findAll();
    }

    public Restricciones save(Restricciones restricciones) {
        return restriccionesRepository.save(restricciones);
    }

    public void delete(Long id) {
        restriccionesRepository.deleteById(id);
    }
}
