package com.bitWiseComplexity.bigMeow.Domain.solicitud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudService {
    private final SolicitudRepository solicitudRepository;

    @Autowired
    public SolicitudService(SolicitudRepository solicitudRepository) {
        this.solicitudRepository = solicitudRepository;
    }

    public List<Solicitud> findAll() {
        return solicitudRepository.findAll();
    }

    public Solicitud save(Solicitud solicitud) {
        return solicitudRepository.save(solicitud);
    }

    public void delete(Long id) {
        solicitudRepository.deleteById(id);
    }
}
