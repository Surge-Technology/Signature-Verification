package com.gen.signatureverification.services;

import com.gen.signatureverification.dto.LoginDto;
import com.gen.signatureverification.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    LoginDto login(LoginDto loginDto);
}
