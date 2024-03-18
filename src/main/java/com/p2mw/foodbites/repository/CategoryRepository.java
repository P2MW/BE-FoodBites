package com.p2mw.foodbites.repository;

import com.p2mw.foodbites.enumeration.ECategory;
import com.p2mw.foodbites.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<ECategory> findByName(ECategory ECategory);
}
