package com.example.hackaton_1_mejorada.Domain.usuario;


import com.example.hackaton_1_mejorada.Domain.limites.Limites;
import com.example.hackaton_1_mejorada.Domain.limites.dto.LimiteRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private LimiteRepository limiteRepository;

    // 1. Crear usuario
    public Usuario crearUsuario(Usuario usuario) {
        // Validar que la empresa exista
        Empresa empresa = empresaRepository.findById(usuario.getEmpresa().getId())
                .orElseThrow(() -> new NoSuchElementException("Empresa no encontrada"));
        usuario.setEmpresa(empresa);
        return usuarioRepository.save(usuario);
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
        Usuario usuario = obtenerUsuarioPorId(id);
        usuario.setNombre(usuarioActualizado.getNombre());
        usuario.setCorreo(usuarioActualizado.getCorreo());
        usuario.setRol(usuarioActualizado.getRol());
        return usuarioRepository.save(usuario);
    }

    // 5. Asignar límite a usuario
    public Limite asignarLimite(Long usuarioId, LimiteRequestDTO limiteRequest) {
        Usuario usuario = obtenerUsuarioPorId(usuarioId);
        Limite limite = new Limite();
        limite.setTipoModelo(limiteRequest.getTipoModelo());
        limite.setLimiteSolicitudes(limiteRequest.getLimiteSolicitudes());
        limite.setLimiteTokens(limiteRequest.getLimiteTokens());
        limite.setUsuario(usuario);
        return limiteRepository.save(limite);
    }

    // 6. Generar reporte de consumo (simplificado)
    public String generarReporteConsumo(Long usuarioId) {
        // Lógica para calcular consumo (ej: tokens usados, solicitudes realizadas)
        return "Reporte de consumo para usuario ID: " + usuarioId +
                " (ejemplo: 100 tokens usados en los últimos 7 días)";
    }
}