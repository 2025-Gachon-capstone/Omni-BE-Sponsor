package org.example.omnibesponsor.repository;

import org.example.omnibesponsor.entity.Benefit;
import org.example.omnibesponsor.entity.type.BenefitStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BenefitRepository extends JpaRepository<Benefit, Long> {

    // BEFORE -> ONGOING
    @Query("SELECT b FROM Benefit b WHERE :today BETWEEN b.startDate AND b.endDate AND b.status NOT IN ('ONGOING', 'DELETED', 'PENDING')")
    List<Benefit> findOngoingTargets(@Param("today") LocalDate today);

    // ONGOING -> EXPIRED
    @Query("SELECT b FROM Benefit b WHERE b.endDate < :today AND b.status NOT IN ('EXPIRED', 'DELETED', 'PENDING')")
    List<Benefit> findExpiredTargets(@Param("today") LocalDate today);

}
