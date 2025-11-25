package com.matheusdev.store.repositories;

import com.matheusdev.store.enteties.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
