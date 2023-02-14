package com.financies.financiesapi.services;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.financies.financiesapi.handlers.BusinessException;
import com.financies.financiesapi.mappers.TypeIncomeMapper;
import com.financies.financiesapi.model.dtos.TypeIncomeDTO;
import com.financies.financiesapi.model.entities.TypeIncome;
import com.financies.financiesapi.model.entities.User;
import com.financies.financiesapi.repositories.TypeIncomeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TypeIncomeService {

	private final TypeIncomeRepository typeIncomeRepository;

	private final UserService userService;

	public void create(String description) {

		var userLogged = userService.getUserLogged();

		if (existsByDescription(description, userLogged)) {
			throw new BusinessException("Type of income already exists");
		}

		var typeIncomeService = TypeIncome.builder().id(null).description(description).user(userLogged).build();

		typeIncomeRepository.save(typeIncomeService);
	}

	public void update(TypeIncomeDTO typeIncomeDTO) {

		var typeIncome = typeIncomeRepository.findById(typeIncomeDTO.getId());

		if ( existsByDescription(typeIncomeDTO.getDescription(), userService.getUserLogged()) ) {
			throw new BusinessException("Type of income already exists");
		}

		typeIncome.get().setDescription(typeIncomeDTO.getDescription());
		typeIncomeRepository.save(typeIncome.get());
	}

	public List<TypeIncomeDTO> getAll(Pageable pageable) {

		return TypeIncomeMapper.INSTANCE
				.listEntityToListDTO(typeIncomeRepository.findAllByUser(userService.getUserLogged(), pageable));

	}

	private boolean existsByDescription(String description, User user) {
		return typeIncomeRepository.existsByDescriptionIgnoreCaseAndUser(description, user);
	}

}
