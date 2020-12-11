package com.example.book.controller;

import com.example.book.config.jwt.JwtResponse;
import com.example.book.model.postgresql.dto.UserDto;
import com.example.book.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register")
	public ResponseEntity<JwtResponse> register(@RequestBody UserDto dto) {
		userService.register(dto);
		return ResponseEntity.ok(userService.authenticate(dto.getUsername(),dto.getPassword()));
	}

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody UserDto dto) {
		return ResponseEntity.ok(userService.login(dto));
	}
}
