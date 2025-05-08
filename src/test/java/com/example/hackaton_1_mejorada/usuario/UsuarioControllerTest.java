package com.example.hackaton_1_mejorada.usuario;
// importar clase snecesarias

import com.example.hackaton_1_mejorada.Domain.usuario.Usuario;
import com.example.hackaton_1_mejorada.Domain.usuario.UsuarioController;
import com.example.hackaton_1_mejorada.Domain.usuario.UsuarioService;

import com.example.hackaton_1_mejorada.Domain.limites.Limites;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService; // Mock del Service

    @InjectMocks
    private UsuarioController usuarioController; // Controller bajo test (con el mock inyectado)

    // --- Tests para cada endpoint ---

    @Test
    public void crearUsuario_ReturnsUsuario_WhenRequestIsValid() {
        // 1. Configuración del mock
        Usuario usuarioRequest = new Usuario();
        usuarioRequest.setNombre("Juan");
        usuarioRequest.setCorreo("juan@empresa.com");

        Usuario usuarioResponse = new Usuario();
        usuarioResponse.setId(1L);
        usuarioResponse.setNombre("Juan");
        usuarioResponse.setCorreo("juan@empresa.com");

        when(usuarioService.crearUsuario(usuarioRequest, 1L)).thenReturn(usuarioResponse);

        // 2. Llamada al método del controller
        ResponseEntity<Usuario> response = usuarioController.crearUsuario(usuarioRequest, 1L);

        // 3. Verificaciones
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Juan", response.getBody().getNombre());
        verify(usuarioService, times(1)).crearUsuario(usuarioRequest, 1L);
    }

    @Test
    public void listarUsuariosPorEmpresa_ReturnsList_WhenEmpresaExists() {
        // 1. Configuración del mock
        Usuario usuario1 = new Usuario();
        usuario1.setId(1L);
        Usuario usuario2 = new Usuario();
        usuario2.setId(2L);

        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);
        when(usuarioService.listarUsuariosPorEmpresa(1L)).thenReturn(usuarios);

        // 2. Llamada al método del controller
        ResponseEntity<List<Usuario>> response = usuarioController.listarUsuariosPorEmpresa(1L);

        // 3. Verificaciones
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(usuarioService, times(1)).listarUsuariosPorEmpresa(1L);
    }

    @Test
    public void obtenerUsuario_ReturnsUsuario_WhenUsuarioExists() {
        // 1. Configuración del mock
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Ana");

        when(usuarioService.obtenerUsuarioPorId(1L)).thenReturn(usuario);

        // 2. Llamada al método del controller
        ResponseEntity<Usuario> response = usuarioController.obtenerUsuario(1L);

        // 3. Verificaciones
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Ana", response.getBody().getNombre());
    }

    @Test
    public void obtenerUsuario_ThrowsException_WhenUsuarioNotFound() {
        // 1. Configuración del mock para lanzar excepción
        when(usuarioService.obtenerUsuarioPorId(99L))
                .thenThrow(new NoSuchElementException("Usuario no encontrado"));

        // 2. Verificación de la excepción
        assertThrows(NoSuchElementException.class, () -> {
            usuarioController.obtenerUsuario(99L);
        });
    }

    @Test
    public void actualizarUsuario_ReturnsUpdatedUsuario_WhenRequestIsValid() {
        // 1. Configuración del mock
        Usuario usuarioActualizado = new Usuario();
        usuarioActualizado.setNombre("Ana Actualizada");

        Usuario usuarioGuardado = new Usuario();
        usuarioGuardado.setId(1L);
        usuarioGuardado.setNombre("Ana Actualizada");

        when(usuarioService.actualizarUsuario(1L, usuarioActualizado)).thenReturn(usuarioGuardado);

        // 2. Llamada al método del controller
        ResponseEntity<Usuario> response = usuarioController.actualizarUsuario(1L, usuarioActualizado);

        // 3. Verificaciones
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Ana Actualizada", response.getBody().getNombre());
    }

    @Test
    public void asignarLimite_ReturnsLimite_WhenRequestIsValid() {
        // 1. Configuración del mock
        Limites limiteRequest = new Limites();
        Limites limiteResponse = new Limites();
        limiteResponse.setId(1L);

        when(usuarioService.asignarLimite(1L, limiteRequest)).thenReturn(limiteResponse);

        // 2. Llamada al método del controller
        ResponseEntity<Limites> response = usuarioController.asignarLimite(1L, limiteRequest);

        // 3. Verificaciones
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody().getId());
    }

    @Test
    public void obtenerReporteConsumo_ReturnsString_WhenUsuarioExists() {
        // 1. Configuración del mock
        String reporte = "El usuario consumió: 50 tokens";
        when(usuarioService.generarReporteConsumo(1L)).thenReturn(reporte);

        // 2. Llamada al método del controller
        ResponseEntity<String> response = usuarioController.obtenerReporteConsumo(1L);

        // 3. Verificaciones
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("tokens"));
    }
}
