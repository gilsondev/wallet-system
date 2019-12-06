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

    private final Long ID = 1L;

    private final String USER = "User Test";

    private final String PASSWORD = "123123";

    private final String EMAIL = "user.test@mail.com";

    private final String URL = "/user";

    @Test
    public void shouldCreateUser() throws Exception {
        when(userService.save(any(User.class))).thenReturn(getMockUser());
        String content = getJSONPayload(null, USER, EMAIL, PASSWORD);

        mvc.perform(post(URL)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.id").value(ID))
                .andExpect(jsonPath("$.data.name").value(USER))
                .andExpect(jsonPath("$.data.email").value(EMAIL));
    }

    @Test
    public void shouldNotCreateUser() throws Exception {
        String content = getJSONPayload(null, USER, "wrong", PASSWORD);

        mvc.perform(post(URL)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]").value("E-mail is invalid"));
    }

    private User getMockUser() {
        User user = new User();
        user.setId(ID);
        user.setName(USER);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);

        return user;
    }

    private String getJSONPayload(Long id, String name, String email, String password) throws JsonProcessingException {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setName(name);
        userDTO.setEmail(email);
        userDTO.setPassword(password);

        return objectMapper.writeValueAsString(userDTO);
    }
}
