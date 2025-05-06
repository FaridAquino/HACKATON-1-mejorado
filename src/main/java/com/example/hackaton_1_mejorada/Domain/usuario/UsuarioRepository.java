package com.bitWiseComplexity.bigMeow.Domain.usuario;

import com.bitWiseComplexity.bigMeow.Domain.usuario.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
