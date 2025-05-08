package com.example.hackaton_1_mejorada.Domain.usuario;

import com.example.hackaton_1_mejorada.Domain.limites.Limites;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company/users")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PreAuthorize("hasRole('ROLE_COMPANY_ADMIN')")
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario, @RequestParam Long id) {
        Usuario nuevoUsuario = usuarioService.crearUsuario(usuario, id);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @PreAuthorize("hasRole('ROLE_COMPANY_ADMIN')")
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuariosPorEmpresa(@RequestParam Long id) {
        List<Usuario> usuarios = usuarioService.listarUsuariosPorEmpresa(id);
        return ResponseEntity.ok(usuarios);
    }

    @PreAuthorize("hasRole('ROLE_COMPANY_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @PreAuthorize("hasRole('ROLE_COMPANY_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(
            @PathVariable Long id,
            @RequestBody Usuario usuarioActualizado) {
        Usuario usuario = usuarioService.actualizarUsuario(id, usuarioActualizado);
        return ResponseEntity.ok(usuario);
    }

    @PreAuthorize("hasRole('ROLE_COMPANY_ADMIN')")
    @PostMapping("/{id}/limits")
    public ResponseEntity<Limites> asignarLimite(@PathVariable Long id, @RequestBody Limites limites) {
        Limites limite = usuarioService.asignarLimite(id, limites);
        return ResponseEntity.ok(limite);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}/consumption")
    public ResponseEntity<String> obtenerReporteConsumo(@PathVariable Long id) {
        String reporte = usuarioService.generarReporteConsumo(id);
        return ResponseEntity.ok(reporte);
    }
}