package com.financies.financiesapi.services;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.financies.financiesapi.handlers.BusinessException;
import com.financies.financiesapi.mappers.TypeExpenseMapper;
import com.financies.financiesapi.model.dtos.TypeExpenseDTO;
import com.financies.financiesapi.model.entities.TypeExpense;
import com.financies.financiesapi.model.entities.User;
import com.financies.financiesapi.repositories.TypeExpenseRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TypeExpenseService {

	private final TypeExpenseRepository typeExpenseRepository;
	private final UserService userService;

	public void createTypeExpense(String description) {

		var userLogged = userService.getUserLogged();

		if (existsByDescription(description, userLogged)) {
			throw new BusinessException("Type of expense already exist");
		}
		var typeExpense = TypeExpense.builder().id(null).description(description).user(userLogged).build();

		typeExpenseRepository.save(typeExpense);
	}

	public List<TypeExpenseDTO> getAll(Pageable pageable) {
		return TypeExpenseMapper.INSTANCE
				.listEntityToListDTO(typeExpenseRepository.findAllByUser(userService.getUserLogged(), pageable));
	}
	
	public void updateTypeExpense(TypeExpenseDTO typeExpenseDTO) {
		
		var typeExpense = typeExpenseRepository.findById(typeExpenseDTO.getId());
		
		if(existsByDescription(typeExpenseDTO.getDescription(), userService.getUserLogged())) {
			throw new BusinessException("Type of Expense already exist and for this you cannot update this type");
		}
		
		typeExpense.get().setDescription(typeExpenseDTO.getDescription());
		typeExpenseRepository.save(typeExpense.get());
	}
	
	public void deleteTypeExpense(Integer id) {
		
		if(!existsByIdAndUser(id, userService.getUserLogged())) {
			throw new BusinessException("Type doesn't exist and for this cannot be deleted");
		}
		
		typeExpenseRepository.deleteById(id);
	}
	
	
	private boolean existsByIdAndUser(Integer id, User user) {
		return typeExpenseRepository.existsByIdAndUser(id, user);
	}

	private boolean existsByDescription(String description, User user) {
		return typeExpenseRepository.existsByDescriptionIgnoreCaseAndUser(description, user);
	}

}
