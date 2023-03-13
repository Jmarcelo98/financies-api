package com.financies.financiesapi.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.financies.financiesapi.configs.security.JWTUtils;
import com.financies.financiesapi.handlers.ConflictException;
import com.financies.financiesapi.handlers.ResourceNotFoundException;
import com.financies.financiesapi.mappers.UserMapper;
import com.financies.financiesapi.mappers.UserNamePhotoMapper;
import com.financies.financiesapi.mappers.UserRegisterMapper;
import com.financies.financiesapi.model.dtos.UserDTO;
import com.financies.financiesapi.model.dtos.UserNamePhotoDTO;
import com.financies.financiesapi.model.dtos.UserRegisterDTO;
import com.financies.financiesapi.model.entities.User;
import com.financies.financiesapi.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private final JWTUtils jwtUtils;

	private final UserRepository userRepository;

	public UserDTO getUser() {

		return UserMapper.INSTANCE.entityToDTO(getUserLogged());

	}

	public UserNamePhotoDTO getNamePhoto() {
		return UserNamePhotoMapper.INSTANCE.entityToDTO(getUserLogged());
	}

	public UserDTO create(UserRegisterDTO userRegisterDTO) {

		if (existsByEmail(userRegisterDTO.getEmail())) {
			throw new ConflictException("E-mail already registered.");
		}

		final BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();

		userRegisterDTO.setPassword(bCrypt.encode(userRegisterDTO.getPassword()));

		var user = UserRegisterMapper.INSTANCE.DTOToEntity(userRegisterDTO);

		userRepository.save(user);

		return UserMapper.INSTANCE.entityToDTO(user);

	}

	public User getUserLogged() {
		return userRepository.findByEmailIgnoreCase(jwtUtils.getPrincipal())
				.orElseThrow(() -> new ResourceNotFoundException("User not found by email"));
	}

	public User findByEmail(String email) {
		return userRepository.findByEmailIgnoreCase(email)
				.orElseThrow(() -> new ResourceNotFoundException("User not found by email"));
	}

	private Boolean existsByEmail(String email) {
		return userRepository.existsByEmailIgnoreCase(email);
	}

}
