package com.financies.financiesapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financies.financiesapi.model.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmailIgnoreCase(String email);
	
	Boolean existsByEmailIgnoreCase(String email);

}
