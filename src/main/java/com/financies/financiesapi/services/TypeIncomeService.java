package com.financies.financiesapi.services;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.financies.financiesapi.mappers.TypeIncomeMapper;
import com.financies.financiesapi.model.dtos.TypeIncomeDTO;
import com.financies.financiesapi.repositories.TypeIncomeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TypeIncomeService {

	private final TypeIncomeRepository typeIncomeRepository;

	private final UserService user;

	public List<TypeIncomeDTO> getAll(Pageable pageable) {

		var list = typeIncomeRepository.findAllByUser(user.getUserLogged(), pageable);

		return TypeIncomeMapper.INSTANCE.listEntityToListDTO(list);

	}

}
