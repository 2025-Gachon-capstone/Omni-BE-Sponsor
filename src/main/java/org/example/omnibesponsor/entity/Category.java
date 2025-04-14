package org.example.omnibesponsor.entity;


import jakarta.persistence.*;
import lombok.*;
import org.example.omnibesponsor.entity.base.BaseEntity;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Category")
public class Category extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(nullable = false,unique = true)
    private String title;

    @Version
    private Long version;

}
