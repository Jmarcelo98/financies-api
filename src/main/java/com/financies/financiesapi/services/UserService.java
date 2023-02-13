package com.financies.financiesapi.services;

import org.springframework.stereotype.Service;

import com.financies.financiesapi.configs.security.JWTUtils;
import com.financies.financiesapi.handlers.ResourceNotFoundException;
import com.financies.financiesapi.model.entities.User;
import com.financies.financiesapi.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private final JWTUtils jwtUtils;

	private final UserRepository userRepository;

	public User getUserLogged() {
		System.err.println("jwtUtils.getPrincipal(): " + jwtUtils.getPrincipal());
		return userRepository.findByEmailIgnoreCase(jwtUtils.getPrincipal())
				.orElseThrow(() -> new ResourceNotFoundException("User not found by email"));
	}

	public User findByEmail(String email) {
		return userRepository.findByEmailIgnoreCase(email)
				.orElseThrow(() -> new ResourceNotFoundException("User not found by email"));
	}

}
