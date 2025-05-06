package com.example.hackaton_1_mejorada.Domain.usuario;

import com.example.hackaton_1_mejorada.Domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByEmpresaId(Long empresaId); // Para filtrar usuarios por empresa
}
