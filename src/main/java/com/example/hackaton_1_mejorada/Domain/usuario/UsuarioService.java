package com.example.hackaton_1_mejorada.Domain.usuario;

import com.example.hackaton_1_mejorada.Domain.Empresa.Empresa;
import com.example.hackaton_1_mejorada.Domain.Empresa.EmpresaRepository;
import com.example.hackaton_1_mejorada.Domain.limites.Limites;
import com.example.hackaton_1_mejorada.Domain.limites.LimitesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EmpresaRepository empresaRepository;

    private final LimitesRepository limiteRepository;

    // 1. Crear usuario
    public Usuario crearUsuario(Usuario usuario, Long id_empresa) {
        Empresa empresa= empresaRepository.findById(id_empresa).orElseThrow(()->new RuntimeException("Empresa no encontrada"));
        Usuario usuario1=usuarioRepository.save(usuario);
        usuario1.setEmpresa(empresa);
        return usuarioRepository.save(usuario1);
    }

    // 2. Listar usuarios por empresa
    public List<Usuario> listarUsuariosPorEmpresa(Long empresaId) {
        return usuarioRepository.findByEmpresaId(empresaId);
    }

    // 3. Obtener usuario por ID
    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
    }

    // 4. Actualizar usuario
    public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
        usuario.setNombre(usuarioActualizado.getNombre());
        usuario.setCorreo(usuarioActualizado.getCorreo());
        usuario.setRol(usuarioActualizado.getRol());
        return usuarioRepository.save(usuario);
    }

    // 5. Asignar límite a usuario
    public Limites asignarLimite(Long usuarioId, Limites limites) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        limites.setUsuario(usuario);

        return limiteRepository.save(limites);
    }

    // 6. Generar reporte de consumo (simplificado)
    public String generarReporteConsumo(Long usuarioId) {

        Usuario usuario=usuarioRepository.findById(usuarioId).orElseThrow(()->new RuntimeException("Usuario no encontrado"));
        String texto="El usuario consumió: "+usuario.getTokens_consumidos_en_total()+ "tokens";
        return texto;
    }
}