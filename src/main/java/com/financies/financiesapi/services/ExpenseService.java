package com.financies.financiesapi.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.financies.financiesapi.mappers.ExpenseMapper;
import com.financies.financiesapi.model.dtos.ExpenseDTO;
import com.financies.financiesapi.repositories.ExpenseRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ExpenseService {
	
	private final ExpenseRepository expenseRepository;
	
	private final UserService userService;
	
	public Page<ExpenseDTO> getAll(Pageable pageable){
		
		var list = expenseRepository.findAllByUser(userService.getUserLogged(), pageable);
		
		return ExpenseMapper.INSTANCE.pageEntityToPageDTO(list);
	}

}
