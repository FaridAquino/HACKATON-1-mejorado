package com.example.hackaton_1_mejorada.Service;

import com.example.hackaton_1_mejorada.Domain.limites.Limites;
import com.example.hackaton_1_mejorada.Domain.limites.LimitesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResetLimitesService {

    private final LimitesRepository limitesRepository;

    @Scheduled(fixedRate = 180000) // Cada 3 minutos
    public void resetearLimites() {
        List<Limites> limites = limitesRepository.findAll();
        for (Limites limite : limites) {
            limite.setTokenSobrantes(5); // Valor inicial deseado
        }
        limitesRepository.saveAll(limites);
        System.out.println("Se reiniciaron los tokens de los usuarios a 5");
    }
}