package org.example.shoestorejava.controllers;


import org.example.shoestorejava.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.example.shoestorejava.repositories.ShoppingCartRepository;

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;
    private ShoppingCartRepository shoppingCartRepository;

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestParam Long userId,
                                            @RequestParam Long shoeId,
                                            @RequestParam int quantity) {
        shoppingCartService.addToCart(userId, shoeId, quantity);
        return ResponseEntity.ok("Shoe added to cart successfully");
    }

    @PostMapping("/checkout")
    public ResponseEntity<String> checkout(@RequestParam Long userId) {
        shoppingCartService.checkout(userId);
        return ResponseEntity.ok("Checkout completed successfully");
    }
}
