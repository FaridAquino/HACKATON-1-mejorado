package com.example.hackaton_1_mejorada.Domain.sparky;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SparkyRepository extends JpaRepository<Sparky, Long> {
}
