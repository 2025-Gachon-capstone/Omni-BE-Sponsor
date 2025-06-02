package org.example.omnibesponsor.repository;

import org.example.omnibesponsor.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findBySponsor_Category_CategoryId(Long categoryId, Pageable pageable);

    @EntityGraph(attributePaths = {"sponsor", "productCategory"})
    Page<Product> findByProductCategory_ProductCategoryId(Long productCategoryId, Pageable pageable);

    @EntityGraph(attributePaths = {"sponsor", "productCategory"})
    Page<Product> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"sponsor", "productCategory"})
    Optional<Product> findById(Long productId);
}
