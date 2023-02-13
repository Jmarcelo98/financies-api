package com.financies.financiesapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financies.financiesapi.model.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
