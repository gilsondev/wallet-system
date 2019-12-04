package com.wallet.repository;

import com.wallet.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void shouldCreateUser() {
        User user = new User();
        user.setName("User Test");
        user.setEmail("user.test@mail.com");
        user.setPassword("123");

        User savedUser = userRepository.save(user);

        assertThat(savedUser).isNotNull();
    }
}
