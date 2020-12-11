package com.example.book.config.jwt;

import com.example.book.model.postgresql.entity.User;
import com.example.book.repository.postgresql.UserRepository;
import com.example.book.utils.ErrorCodes;
import com.example.book.utils.ResponseException;
import com.example.book.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private  PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username);
		if (user == null) {
			onUserNotFound();
		}
		return user;
	}

	@NonNull
	public User getCurrentUser() {
		final Optional<String> currentUser = SecurityUtils.getCurrentUserLogin();

		if (currentUser.isEmpty())
			onUserNotFound();

		final String currentUserName = currentUser.get();
		final User user = userRepository.findByUsername(currentUserName);

		if (user == null)
			onUserNotFound();

		return user;
	}

	private void onUserNotFound() {
		throw ResponseException.newResponseException(ErrorCodes.ERROR_CODE_USER_NOT_FOUND, " User not Found");
	}

	public User save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
}
