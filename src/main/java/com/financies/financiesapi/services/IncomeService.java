package com.financies.financiesapi.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.financies.financiesapi.handlers.BusinessException;
import com.financies.financiesapi.mappers.IncomeMapper;
import com.financies.financiesapi.model.dtos.IncomeDTO;
import com.financies.financiesapi.model.entities.User;
import com.financies.financiesapi.repositories.IncomeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IncomeService {

	private final IncomeRepository incomeRepository;

	private final UserService userService;

	public Page<IncomeDTO> getAll(Pageable pageable) {

		var list = incomeRepository.findAllByUserOrderByDateInclusionDesc(userService.getUserLogged(), pageable);

		return IncomeMapper.INSTANCE.pageEntityToPageDTO(list);

	}

	public IncomeDTO getById(Integer id) {

		var type = incomeRepository.findByIdAndUser(id, userService.getUserLogged());

		if (type == null) {
			throw new BusinessException("You cannot access another user's income");
		}

		return IncomeMapper.INSTANCE.entityToDTO(type);

	}

	public void delete(Integer id) {

		if (!existsByIdAndUser(id, userService.getUserLogged())) {
			throw new BusinessException("Impossible to delete another user's income");
		}

		incomeRepository.deleteById(id);

	}

//	private boolean existsByDescription(String description, User user) {
//		return typeIncomeRepository.existsByDescriptionIgnoreCaseAndUser(description, user);
//	}
//
	private boolean existsByIdAndUser(Integer id, User user) {
		return incomeRepository.existsByIdAndUser(id, user);
	}

}
