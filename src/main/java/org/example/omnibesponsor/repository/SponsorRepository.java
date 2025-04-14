package org.example.omnibesponsor.repository;

import org.example.omnibesponsor.entity.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SponsorRepository extends JpaRepository<Sponsor, Long> {

    boolean existsByMemberId(Long memberId);

}
