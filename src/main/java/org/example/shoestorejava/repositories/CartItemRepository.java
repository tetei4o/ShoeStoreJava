package org.example.shoestorejava.repositories;

import org.example.shoestorejava.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}