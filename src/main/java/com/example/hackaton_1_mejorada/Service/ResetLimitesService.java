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


    @Scheduled(fixedRate = 3600000) // Cada 1 hora
    public void resetearLimites() {
        List<Limites> limites = limitesRepository.findAll();
        for (Limites limite : limites) {
            limite.setValorMaximo(100.0); // Valor inicial deseado
            limite.setValorMinimo(10.0);
        }
        limitesRepository.saveAll(limites);
        System.out.println("Se reiniciaron los valores de límites");
    }
}