package com.financies.financiesapi.configs.security;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.financies.financiesapi.services.security.UserDetailsImpl;

import lombok.AllArgsConstructor;

@SuppressWarnings("deprecation")
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailsImpl userDetailsImpl;

	private final PasswordEncoder passwordEncoder;

	private final String[] ACESS = { "/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
			"/configuration/security", "/swagger-ui.html", "/webjars/**", "/h2/**" };

	private final String[] ACESS_PUBLIC_CONTROLLERS = { "/categories", "/posts/most-recent",
			"/follower/most-followers" };

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsImpl).passwordEncoder(passwordEncoder);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/login").permitAll()
				.antMatchers(ACESS_PUBLIC_CONTROLLERS).permitAll().antMatchers(ACESS).permitAll().anyRequest()
				.authenticated().and().addFilter(new JWTAuthenticateFilter(authenticationManager()))
				.addFilter(new JWTValidateFilter(authenticationManager())).sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().cors().and().csrf().disable();
	}

	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.addAllowedOrigin(CorsConfiguration.ALL);
		config.addAllowedHeader("*");
		config.addAllowedOriginPattern("*");
		config.setAllowedOrigins(Collections.singletonList("*"));
		config.addExposedHeader("Authorization");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("HEAD");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("DELETE");
		config.addAllowedMethod("PATCH");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

}