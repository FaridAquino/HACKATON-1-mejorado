package com.example.hackaton_1_mejorada.AI;

import com.example.hackaton_1_mejorada.Domain.limites.Limites;
import com.example.hackaton_1_mejorada.Domain.usuario.Usuario;
import com.example.hackaton_1_mejorada.Domain.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AIService {
    private final UsuarioRepository usuarioRepository;

    public List<Limites> obtenerModelos(Long id){
        Usuario usuario=usuarioRepository.findById(id).orElseThrow(()->new RuntimeException("No existe ese usuario"));




        return
    }


}
