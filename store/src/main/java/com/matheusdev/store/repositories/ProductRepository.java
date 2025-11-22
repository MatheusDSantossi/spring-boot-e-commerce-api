package com.matheusdev.store.repositories;

import com.matheusdev.store.enteties.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
        List<Product> findByCategoryId(Byte id);  // ✅ Correct - matches your field name

    @Query("SELECT p FROM Product p")
    List<Product> findAllWithCategory();  // Removed @EntityGraph since there's no relationship
}
