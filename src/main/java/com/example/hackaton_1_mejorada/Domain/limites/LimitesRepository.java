package com.example.hackaton_1_mejorada.Domain.limites;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LimitesRepository extends JpaRepository<Limites, Long> {
}
