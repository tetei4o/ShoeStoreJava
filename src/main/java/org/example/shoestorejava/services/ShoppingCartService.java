package org.example.shoestorejava.services;

import org.example.shoestorejava.models.*;
import org.example.shoestorejava.repositories.OrderRepository;
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

    @Autowired
    private OrderRepository orderRepository;

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
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Shopping cart not found for user ID: " + userId));

        if (shoppingCart.getItems().isEmpty()) {
            throw new RuntimeException("Cannot checkout an empty cart");
        }
        User user = shoppingCart.getUser();
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        double totalPrice = 0;
        for (CartItem cartItem : shoppingCart.getItems()) {
            Shoe shoe = cartItem.getShoe();

            if (shoe.getStockQuantity() < cartItem.getQuantity()) {
                throw new RuntimeException("Insufficient stock for shoe: " + shoe.getName());
            }
            shoe.setStockQuantity(shoe.getStockQuantity() - cartItem.getQuantity());
            shoeRepository.save(shoe);
            OrderShoe orderShoe = new OrderShoe();
            orderShoe.setOrder(order);
            orderShoe.setShoe(shoe);
            orderShoe.setQuantity(cartItem.getQuantity());
            order.getOrderShoes().add(orderShoe);
            totalPrice += cartItem.getQuantity() * shoe.getPrice();
        }

        order.setTotalPrice(totalPrice);
        orderRepository.save(order);
        shoppingCart.getItems().clear();
        shoppingCartRepository.save(shoppingCart);
    }

    private ShoppingCart createCartForUser(Long userId) {
        ShoppingCart cart = new ShoppingCart();
        User user = new User();
        user.setId(userId);
        cart.setUser(user);
        return shoppingCartRepository.save(cart);
    }
}