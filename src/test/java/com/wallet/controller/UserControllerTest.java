package com.wallet.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.entity.User;
import com.wallet.service.UserService;
import com.wallet.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {
    @MockBean
    UserService userService;

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    private final String USER = "User Test";

    private final String PASSWORD = "123123";

    private final String EMAIL = "user.test@mail.com";

    private final String URL = "/user";

    @Test
    public void shouldCreateUser() throws Exception {
        when(userService.save(any(User.class))).thenReturn(getMockUser());

        mvc.perform(post(URL)
                .content(getJSONPayload())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    private User getMockUser() {
        User user = new User();
        user.setName(USER);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);

        return user;
    }

    private String getJSONPayload() throws JsonProcessingException {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(USER);
        userDTO.setEmail(EMAIL);
        userDTO.setPassword(PASSWORD);

        return objectMapper.writeValueAsString(userDTO);
    }
}
