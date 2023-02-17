package com.financies.financiesapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financies.financiesapi.model.dtos.UserDTO;
import com.financies.financiesapi.model.dtos.UserRegisterDTO;
import com.financies.financiesapi.services.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	@PostMapping
	public ResponseEntity<UserDTO> create(@RequestBody @Validated UserRegisterDTO userRegisterDTO) {
		return ResponseEntity.ok(userService.create(userRegisterDTO));
	}

}
