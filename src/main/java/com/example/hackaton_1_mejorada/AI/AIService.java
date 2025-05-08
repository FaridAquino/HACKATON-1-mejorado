package com.example.hackaton_1_mejorada.AI;

import com.example.hackaton_1_mejorada.Domain.limites.Limites;
import com.example.hackaton_1_mejorada.Domain.limites.LimitesModelo;
import com.example.hackaton_1_mejorada.Domain.solicitud.solicitudEstado;
import com.example.hackaton_1_mejorada.Domain.usuario.Usuario;
import com.example.hackaton_1_mejorada.Domain.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AIService {
    private final UsuarioRepository usuarioRepository;

    public List<String> obtenerModelos(Long id){
        Usuario usuario=usuarioRepository.findById(id).orElseThrow(()->new RuntimeException("No existe ese usuario"));

        List<Limites> limites=usuario.getLimites();

        List<String> modelos=new ArrayList<>();

        for (Limites limite : limites) {
            if (limite.getModelo()== LimitesModelo.GPT && !modelos.contains("GPT")){
                modelos.add("GPT");
            }

            if (limite.getModelo() == LimitesModelo.LLAMA  && !modelos.contains("LLAMA")){
                modelos.add("LLAMA");
            }

            if (limite.getModelo() == LimitesModelo.LLAMA  && !modelos.contains("DEEPSEEK")){
                modelos.add("DEEPSEEK");
            }
        }


        return modelos;
    }


}
