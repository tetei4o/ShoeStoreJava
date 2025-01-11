package org.example.shoestorejava.repositories;

import org.example.shoestorejava.models.Shoe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoeRepository extends JpaRepository<Shoe, Long> {
    List<Shoe> findByCategoryName(String categoryName);

    List<Shoe> findByBrand(String brand);

}

