package org.example.shoestorejava;

import org.example.shoestorejava.dtos.UserRegistrationDTO;
import org.example.shoestorejava.repositories.UserRepository;
import org.example.shoestorejava.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void registerUser_ShouldThrowException_WhenEmailExists() {

        UserRegistrationDTO dto = new UserRegistrationDTO();
        dto.setEmail("existing@example.com");
        when(userRepository.existsByEmail(dto.getEmail())).thenReturn(true);

        assertThrows(RuntimeException.class, () -> userService.registerUser(dto));
    }
}
