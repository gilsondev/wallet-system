package com.wallet.repository;

import com.wallet.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        User user = new User();
        user.setName("User Setup");
        user.setEmail("user.setup@mail.com");
        user.setPassword("123");
        userRepository.save(user);
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void shouldCreateUser() {
        User user = new User();
        user.setName("User Test");
        user.setEmail("user.test@mail.com");
        user.setPassword("123");

        User savedUser = userRepository.save(user);

        assertThat(savedUser).isNotNull();
    }

    @Test
    public void shouldFindUserByEmail() {
        String email = "user.setup@mail.com";
        Optional<User> response = userRepository.findByEmailEquals(email);

        assertThat(response.isPresent()).isTrue();
    }
}
