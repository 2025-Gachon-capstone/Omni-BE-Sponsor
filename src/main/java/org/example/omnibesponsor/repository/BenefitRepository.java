package org.example.omnibesponsor.repository;

import org.example.omnibesponsor.entity.Benefit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BenefitRepository extends JpaRepository<Benefit, Long> {
}
