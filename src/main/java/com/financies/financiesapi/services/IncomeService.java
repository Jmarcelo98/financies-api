package com.financies.financiesapi.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.financies.financiesapi.mappers.IncomeMapper;
import com.financies.financiesapi.model.dtos.IncomeDTO;
import com.financies.financiesapi.repositories.IncomeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IncomeService {

	private final IncomeRepository incomeRepository;

	private final UserService userService;

	public Page<IncomeDTO> getAll(Pageable pageable) {

		var list = incomeRepository.findAllByUser(userService.getUserLogged(), pageable);

		return IncomeMapper.INSTANCE.pageEntityToPageDTO(list);

	}

//	private boolean existsByDescription(String description, User user) {
//		return typeIncomeRepository.existsByDescriptionIgnoreCaseAndUser(description, user);
//	}
//
//	private boolean existsByIdAndUser(Integer id, User user) {
//		return typeIncomeRepository.existsByIdAndUser(id, user);
//	}

}
