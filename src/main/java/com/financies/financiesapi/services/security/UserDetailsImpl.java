package com.financies.financiesapi.services.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.financies.financiesapi.services.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailsImpl implements UserDetailsService {

	private final UserService userService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		var user = userService.findByEmail(email);
		return new UserDetail(user);
	}

}