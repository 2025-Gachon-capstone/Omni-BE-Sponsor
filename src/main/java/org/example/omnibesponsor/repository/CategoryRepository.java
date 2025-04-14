package org.example.omnibesponsor.repository;

import org.example.omnibesponsor.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByTitle(String title);

}
