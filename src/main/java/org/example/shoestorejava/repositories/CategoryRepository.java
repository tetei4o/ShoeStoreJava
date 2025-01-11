package org.example.shoestorejava.repositories;

import org.example.shoestorejava.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Add custom queries if needed
}
