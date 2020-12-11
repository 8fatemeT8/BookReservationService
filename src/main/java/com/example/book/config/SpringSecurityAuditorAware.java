package com.example.book.config;

import com.example.book.model.postgresql.entity.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SpringSecurityAuditorAware implements AuditorAware<User> {

	@Override
	public Optional<User> getCurrentAuditor() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		return Optional.ofNullable((User) securityContext.getAuthentication().getPrincipal());
	}
}
