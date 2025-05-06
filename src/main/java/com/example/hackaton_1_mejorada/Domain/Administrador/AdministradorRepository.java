package com.bitWiseComplexity.bigMeow.Domain.Administrador;

import com.bitWiseComplexity.bigMeow.Domain.Administrador.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
}
