package org.example.omnibesponsor.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.omnibesponsor.entity.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Sponsor")
public class Sponsor extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sponsorId;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private String sponsorName;

    @Column(nullable = false)
    private String sponsorNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Version
    private Long version;

    @OneToMany(mappedBy = "sponsor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Benefit> benefits = new ArrayList<>();

}
