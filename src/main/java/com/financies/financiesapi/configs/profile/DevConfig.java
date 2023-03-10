package com.financies.financiesapi.configs.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.financies.financiesapi.services.DBService;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@Profile("dev")
public class DevConfig {

	private final DBService dbService;

	@Bean
	public boolean instantiateDatabase() {
		System.err.println("utilizar para criar o banco em H2");
		dbService.createdBDH2();
		return true;
	}

}