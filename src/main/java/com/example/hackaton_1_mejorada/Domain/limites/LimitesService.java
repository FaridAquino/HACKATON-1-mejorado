package com.bitWiseComplexity.bigMeow.Domain.limites;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LimitesService {
    private final LimitesRepository limitesRepository;

    @Autowired
    public LimitesService(LimitesRepository limitesRepository) {
        this.limitesRepository = limitesRepository;
    }

    public List<Limites> findAll() {
        return limitesRepository.findAll();
    }

    public Limites save(Limites limites) {
        return limitesRepository.save(limites);
    }

    public void delete(Long id) {
        limitesRepository.deleteById(id);
    }
}
