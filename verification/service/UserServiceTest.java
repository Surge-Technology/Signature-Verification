package com.gen.eChannel.verification.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.gen.eChannel.verification.dto.LoginDto;
import com.gen.eChannel.verification.dto.UserDto;
import com.gen.eChannel.verification.entities.User;
import com.gen.eChannel.verification.repositories.AddressRepo;
import com.gen.eChannel.verification.repositories.UserRepo;
import com.gen.eChannel.verification.services.impl.UserServiceImpl;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserServiceTest {

    private static final Long USER_ID = 1L;
    private static final String EMAIL = "test@mail.com";
    private static final String PASSWORD = "password";

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepo userRepo;

    @Mock
    private AddressRepo addressRepo;

    @Mock
    private ModelMapper modelMapper;

    private UserDto userDto;
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(USER_ID);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);

        userDto = new UserDto();
        userDto.setId(USER_ID);
        userDto.setEmail(EMAIL);
        userDto.setPassword(PASSWORD);

        LoginDto loginDto = new LoginDto();
        loginDto.setEmail(EMAIL);
        loginDto.setPassword(PASSWORD);

        when(modelMapper.map(any(UserDto.class), eq(User.class))).thenReturn(user);
        when(modelMapper.map(any(User.class), eq(UserDto.class))).thenReturn(userDto);
        when(modelMapper.map(any(User.class), eq(LoginDto.class))).thenReturn(loginDto);

    }

    @Test
    @DisplayName("Create User Test")
    public void shouldCreateUser() {
        // given - precondition or setup
        when(userRepo.findByEmail(EMAIL)).thenReturn(Optional.empty());
        when(userRepo.save(any(User.class))).thenAnswer(i -> i.getArgument(0));

        // when - action and behaviour that we are going to test
        UserDto returnedDto = userService.createUser(userDto);

        // then - verify the result and output using assert statements
        assertNotNull(returnedDto);
        assertEquals(userDto.getId(), returnedDto.getId());
    }

    @Test
    @DisplayName("Get User By Id Test")
    public void shouldGetUserById() {
        // given - precondition or setup
        when(userRepo.findById(USER_ID)).thenReturn(Optional.of(user));

        // when - action and behaviour that we are going to test
        UserDto returnedDto = userService.getUserById(USER_ID);

        // then - verify the result and output using assert statements
        assertNotNull(returnedDto);
        assertEquals(userDto.getId(), returnedDto.getId());
    }

    @Test
    @DisplayName("Login User Test")
    public void shouldLoginUser() {
        // given - precondition or setup
        when(userRepo.findByEmailAndPassword(EMAIL, PASSWORD)).thenReturn(Optional.of(user));

        // when - action and behaviour that we are going to test
        LoginDto returnedDto = userService.login(new LoginDto(EMAIL, PASSWORD));

        // then - verify the result and output using assert statements
        assertNotNull(returnedDto);
        assertEquals(EMAIL, returnedDto.getEmail());
        assertEquals(PASSWORD, returnedDto.getPassword());
    }
}
