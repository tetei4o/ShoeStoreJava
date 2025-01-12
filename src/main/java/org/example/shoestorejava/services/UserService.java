package org.example.shoestorejava.services;

import jakarta.transaction.Transactional;
import org.example.shoestorejava.dtos.UserRegistrationDTO;
import org.example.shoestorejava.models.ShoppingCart;
import org.example.shoestorejava.models.User;
import org.example.shoestorejava.repositories.ShoppingCartRepository;
import org.example.shoestorejava.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final ShoppingCartRepository cartRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       ShoppingCartRepository cartRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    public User registerUser(UserRegistrationDTO registrationDTO) {
        if (userRepository.existsByEmail(registrationDTO.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setEmail(registrationDTO.getEmail());
        user.setPassword(registrationDTO.getPassword());
        user.setFirstName(registrationDTO.getFirstName());
        user.setLastName(registrationDTO.getLastName());

        user = userRepository.save(user);

        ShoppingCart cart = new ShoppingCart();
        cart.setUser(user);
        cartRepository.save(cart);

        return user;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> searchUsersByName(String name) {
        return userRepository.findByNameContaining(name);
    }
}
