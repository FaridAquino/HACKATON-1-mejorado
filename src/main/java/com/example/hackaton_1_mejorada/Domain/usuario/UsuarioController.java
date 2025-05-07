package com.example.hackaton_1_mejorada.Domain.usuario;

import com.example.hackaton_1_mejorada.Domain.limites.Limites;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company/users")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // 1. Crear usuario para una empresa (POST)
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario, @RequestParam Long id) {
        Usuario nuevoUsuario = usuarioService.crearUsuario(usuario, id);
        return ResponseEntity.ok(nuevoUsuario);
    }

    // 2. Listar todos los usuarios de una empresa (GET)
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuariosPorEmpresa(@RequestParam Long id) {
        List<Usuario> usuarios = usuarioService.listarUsuariosPorEmpresa(id);
        return ResponseEntity.ok(usuarios);
    }

    // 3. Obtener información de un usuario específico (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }

    // 4. Actualizar usuario (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(
            @PathVariable Long id,
            @RequestBody Usuario usuarioActualizado) {
        Usuario usuario = usuarioService.actualizarUsuario(id, usuarioActualizado);
        return ResponseEntity.ok(usuario);
    }

    // 5. Asignar límite a un usuario (POST)
    @PostMapping("/{id}/limits")
    public ResponseEntity<Limites> asignarLimite(@PathVariable Long id, @RequestBody Limites limites) {
        Limites limite = usuarioService.asignarLimite(id, limites);
        return ResponseEntity.ok(limite);
    }

    // 6. Obtener reporte de consumo (GET)
    @GetMapping("/{id}/consumption")
    public ResponseEntity<String> obtenerReporteConsumo(@PathVariable Long id) {
        String reporte = usuarioService.generarReporteConsumo(id);
        return ResponseEntity.ok(reporte);
    }
}