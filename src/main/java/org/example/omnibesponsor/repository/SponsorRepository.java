package org.example.omnibesponsor.repository;

import org.example.omnibesponsor.entity.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SponsorRepository extends JpaRepository<Sponsor, Long> {

    boolean existsByMemberId(Long memberId);
    Optional<Sponsor> findByMemberId(Long memberId);
}
