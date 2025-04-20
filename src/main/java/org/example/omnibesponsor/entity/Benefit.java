package org.example.omnibesponsor.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.example.omnibesponsor.entity.base.BaseEntity;
import org.example.omnibesponsor.entity.type.BenefitStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Benefit")
public class Benefit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long benefitId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sponsor_id")
    private Sponsor sponsor;

    private String title;

    private LocalDate startDate;

    private LocalDate endDate;

    private Float discountRate;

    private String targetProduct;

    private int amount;

    private String targetMember;

    @Enumerated(EnumType.STRING)
    private BenefitStatus status = BenefitStatus.PENDING;

    @Version
    private Long version;

}
