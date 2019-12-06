package com.wallet.service;

import com.wallet.entity.User;
import com.wallet.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {
    @MockBean
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @BeforeEach
    public void setUp() {
        when(userRepository.findByEmailEquals(anyString())).thenReturn(Optional.of(new User()));
    }

    @Test
    public void shouldFindUserByEmail() throws Exception {
        Optional<User> response = userService.findByEmail("user.test@mail.com");

        assertThat(response.isPresent()).isTrue();
    }
}
