package com.example.book.service;

import com.example.book.config.jwt.JwtResponse;
import com.example.book.config.jwt.JwtTokenUtil;
import com.example.book.config.jwt.JwtUserDetailsService;
import com.example.book.model.postgresql.dto.UserDto;
import com.example.book.model.postgresql.entity.User;
import com.example.book.repository.postgresql.UserRepository;
import com.example.book.utils.ErrorCodes;
import com.example.book.utils.ResponseException;
import com.example.book.utils.ValidationUtils;
import com.example.book.utils.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final AuthenticationManager authenticationManager;
	private final JwtTokenUtil jwtTokenUtil;
	private final JwtUserDetailsService userDetailsService;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, AuthenticationManager authenticationManager,
					   JwtTokenUtil jwtTokenUtil, JwtUserDetailsService userDetailsService, UserMapper userMapper, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtil = jwtTokenUtil;
		this.userDetailsService = userDetailsService;
		this.userMapper = userMapper;
		this.passwordEncoder = passwordEncoder;
	}

	public JwtResponse authenticate(String username, String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

			final String token = jwtTokenUtil.generateToken(userDetailsService.loadUserByUsername(username));

			return new JwtResponse(token);
		} catch (DisabledException e) {
			throw ResponseException.newResponseException(HttpStatus.UNAUTHORIZED.value(), "Unauthorized - DisabledException ");
		} catch (BadCredentialsException e) {
			throw ResponseException.newResponseException(HttpStatus.UNAUTHORIZED.value(), "Unauthorized - BadCredentialsException");
		}
	}

	public void register(UserDto dto) {
		// validate user
		ValidationUtils.validateUser(dto);

		if (ValidationUtils.isInvalidEmailAddress(dto.getEmail()))
			throw ResponseException.newResponseException(ErrorCodes.ERROR_CODE_INVALID_EMAIL, " Invalid Email");

		if (userRepository.findByUsername(dto.getUsername()) != null)
			throw ResponseException.newResponseException(ErrorCodes.ERROR_CODE_USER_ALREADY_EXIST, " ERROR_CODE_USER_ALREADY_EXIST ");

		if (userRepository.findByEmail(dto.getEmail()) != null)
			throw ResponseException.newResponseException(ErrorCodes.ERROR_CODE_EMAIL_ALREADY_EXIST, " ERROR_CODE_EMAIL_ALREADY_EXIST ");

		// add user
		User user = userMapper.toEntityFromDto(dto);
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		userRepository.save(user);

	}

	public JwtResponse login(UserDto dto) {
		ValidationUtils.validateUser(dto);

		User user = userRepository.findByUsername(dto.getUsername());

		if (user == null)
			throw new ResponseException(ErrorCodes.ERROR_CODE_USER_NOT_FOUND, "user.not.found");

		return authenticate(dto.getUsername(), dto.getPassword());
	}

	public User loadByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}

