package org.example.shoestorejava.repositories;

import org.example.shoestorejava.models.Shoe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoeRepository extends JpaRepository<Shoe, Long> {
    // Add custom queries if needed
}

