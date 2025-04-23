package org.example.omnibesponsor.repository;

import org.example.omnibesponsor.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
