package com.gen.signatureverification.controllers;

import com.gen.signatureverification.dto.LoginDto;
import com.gen.signatureverification.dto.UserDto;
import com.gen.signatureverification.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserService userService;


	@PostMapping("/createUser")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {

		UserDto createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<LoginDto> login(@Valid @RequestBody LoginDto loginDto) {

		LoginDto loggedInUser = userService.login(loginDto);
		return ResponseEntity.ok(loggedInUser);

	}

	@GetMapping("/users")
	public ResponseEntity<List<UserDto>> getAllUsers() {

		List<UserDto> userDtoList = userService.getAllUsers();
		return new ResponseEntity<>(userDtoList, HttpStatus.OK);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) {

		UserDto userDto = userService.getUserById(userId);
		return new ResponseEntity<>(userDto, HttpStatus.OK);

	}
}
