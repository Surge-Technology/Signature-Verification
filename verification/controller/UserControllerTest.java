package com.gen.eChannel.verification.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gen.eChannel.verification.dto.LoginDto;
import com.gen.eChannel.verification.dto.UserDto;
import com.gen.eChannel.verification.services.UserService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Create User Test")
    public void shouldCreateUser() throws Exception {
        // given - precondition or setup
        UserDto userDto = new UserDto();
        userDto.setEmail("test@test.com");
        userDto.setPassword("testPassword");
        userDto.setUserName("testUser");

        // when - action and behaviour that we are going to test
        when(userService.createUser(any(UserDto.class))).thenReturn(userDto);

        // then - verify the result and output using assert statements
        mockMvc.perform(post("/createUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(userDto)));
    }

    @Test
    @DisplayName("Login User Test")
    public void shouldLoginUser() throws Exception {
        // given - precondition or setup
        LoginDto loginDto = new LoginDto();

        // when - action and behaviour that we are going to test
        when(userService.login(any(LoginDto.class))).thenReturn(loginDto);

        // then - verify the result and output using assert statements
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(loginDto)));
    }

    @Test
    @DisplayName("Get All Users Test")
    public void shouldGetAllUsers() throws Exception {
        // given - precondition or setup
        List<UserDto> userDtoList = new ArrayList<>();

        // when - action and behaviour that we are going to test
        when(userService.getAllUsers()).thenReturn(userDtoList);

        // then - verify the result and output using assert statements
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(userDtoList)));
    }

    @Test
    @DisplayName("Get Assigned Events By User Id Test")
    public void shouldGetUserById() throws Exception {
        // given - precondition or setup
        UserDto userDto = new UserDto();

        // when - action and behaviour that we are going to test
        when(userService.getUserById(anyLong())).thenReturn(userDto);

        // then - verify the result and output using assert statements
        mockMvc.perform(get("/user/{userId}", 1))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(userDto)));
    }
}
