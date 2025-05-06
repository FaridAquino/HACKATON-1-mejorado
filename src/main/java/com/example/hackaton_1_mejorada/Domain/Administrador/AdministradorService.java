package com.bitWiseComplexity.bigMeow.Domain.Administrador;

import com.bitWiseComplexity.bigMeow.Domain.Administrador.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorService {
    private final AdministradorRepository administradorRepository;

    public AdministradorService(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    public List<Administrador> findAll() {
        return administradorRepository.findAll();
    }

    public Administrador save(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    public void delete(Long id) {
        administradorRepository.deleteById(id);
    }
}
