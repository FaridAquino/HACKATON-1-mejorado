package com.bitWiseComplexity.bigMeow.Domain.restricciones;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestriccionesRepository extends JpaRepository<Restricciones, Long> {
}
