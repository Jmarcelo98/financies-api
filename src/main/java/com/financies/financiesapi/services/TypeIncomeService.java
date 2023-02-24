package com.financies.financiesapi.services;

import org.springframework.data.domain.Page;
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

	public void create(TypeIncomeDTO typeIncomeDTO) {

		var userLogged = userService.getUserLogged();

		if (existsByDescription(typeIncomeDTO.getDescription(), userLogged)) {
			throw new BusinessException("Type of income already exists");
		}

		var typeIncomeService = TypeIncome.builder().id(null).description(typeIncomeDTO.getDescription())
				.user(userLogged).build();

		typeIncomeRepository.save(typeIncomeService);
	}

	public void update(TypeIncomeDTO typeIncomeDTO) {

		var typeIncome = typeIncomeRepository.findById(typeIncomeDTO.getId());

		if (existsByDescription(typeIncomeDTO.getDescription(), userService.getUserLogged())) {
			throw new BusinessException("Type of income already exists");
		}

		typeIncome.get().setDescription(typeIncomeDTO.getDescription());
		typeIncomeRepository.save(typeIncome.get());
	}

	public void delete(Integer id) {

		if (!existsByIdAndUser(id, userService.getUserLogged())) {
			throw new BusinessException("Impossible to delete another user's income type");
		}

		typeIncomeRepository.deleteById(id);

	}

	public TypeIncomeDTO getById(Integer id) {

		var type = typeIncomeRepository.findByIdAndUser(id, userService.getUserLogged());

		if (type == null) {
			throw new BusinessException("You cannot access another user's income type");
		}

		return TypeIncomeMapper.INSTANCE.entityToDTO(type);

	}

	public Page<TypeIncomeDTO> getAll(Pageable pageable) {

		var list = typeIncomeRepository.findAllByUser(userService.getUserLogged(), pageable);

		return TypeIncomeMapper.INSTANCE.pageEntityToPageDTO(list);

	}

	private boolean existsByDescription(String description, User user) {
		return typeIncomeRepository.existsByDescriptionIgnoreCaseAndUser(description, user);
	}

	private boolean existsByIdAndUser(Integer id, User user) {
		return typeIncomeRepository.existsByIdAndUser(id, user);
	}

}
