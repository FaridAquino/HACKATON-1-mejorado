package com.example.hackaton_1_mejorada.Domain.sparky;

import com.example.hackathon.entity.Sparky;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SparkyRepository extends JpaRepository<Sparky, Long> {
}
