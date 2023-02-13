package com.financies.financiesapi.configs.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.financies.financiesapi.model.entities.User;
import com.financies.financiesapi.services.security.UserDetail;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JWTAuthenticateFilter extends UsernamePasswordAuthenticationFilter {

	private static final int TOKEN_EXPIRATION = 3600000;

	public static final String TOKEN_PASSWORD = "261fcbc0-a023-46c8-a783-57d155d6363b";

	private final AuthenticationManager authenticationManager;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {

			var user = new ObjectMapper().readValue(request.getInputStream(), User.class);

			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), new ArrayList<>()));

		} catch (IOException e) {
			throw new RuntimeException("Falha ao autenticar usuario", e);
		}

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		var detailUser = (UserDetail) authResult.getPrincipal();

		var token = JWT.create().withSubject(detailUser.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
				.sign(Algorithm.HMAC512(TOKEN_PASSWORD));

		response.getWriter().write(token);
		response.getWriter().flush();

	}

}