package org.example.shoestorejava.services;

import org.example.shoestorejava.models.*;
import org.example.shoestorejava.repositories.ShoeRepository;
import org.example.shoestorejava.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ShoeRepository shoeRepository;

    @Transactional
    public void addToCart(Long userId, Long shoeId, int quantity) {
        ShoppingCart cart = shoppingCartRepository.findByUserId(userId)
                .orElseGet(() -> createCartForUser(userId));

        Shoe shoe = shoeRepository.findById(shoeId)
                .orElseThrow(() -> new RuntimeException("Shoe not found"));

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getShoe().getId().equals(shoeId))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            CartItem newItem = new CartItem();
            newItem.setShoe(shoe);
            newItem.setQuantity(quantity);
            newItem.setShoppingCart(cart);
            cart.getItems().add(newItem);
        }
        shoppingCartRepository.save(cart);
    }

    @Transactional
    public void checkout(Long userId) {
        ShoppingCart cart = shoppingCartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Shopping cart not found for user ID: " + userId));


        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        for (CartItem item : cart.getItems()) {
            Order order = new Order();
            order.setShoe(item.getShoe());
            order.setQuantity(item.getQuantity());
            order.setTotalPrice(item.getShoe().getPrice() * item.getQuantity());
            order.setOrderDate(LocalDateTime.now());
            order.setUser(cart.getUser());
        }

        cart.getItems().clear();
        shoppingCartRepository.save(cart);
    }

    private ShoppingCart createCartForUser(Long userId) {
        ShoppingCart cart = new ShoppingCart();
        User user = new User();
        user.setId(userId);
        cart.setUser(user);
        return shoppingCartRepository.save(cart);
    }
}