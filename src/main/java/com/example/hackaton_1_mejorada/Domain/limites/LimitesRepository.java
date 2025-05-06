package com.bitWiseComplexity.bigMeow.Domain.limites;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LimitesRepository extends JpaRepository<Limites, Long> {
}
