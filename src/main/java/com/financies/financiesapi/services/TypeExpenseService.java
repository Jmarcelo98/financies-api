package com.financies.financiesapi.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.financies.financiesapi.handlers.BusinessException;
import com.financies.financiesapi.mappers.TypeExpenseMapper;
import com.financies.financiesapi.mappers.TypeIncomeMapper;
import com.financies.financiesapi.model.dtos.TypeExpenseDTO;
import com.financies.financiesapi.model.dtos.TypeIncomeDTO;
import com.financies.financiesapi.model.entities.TypeExpense;
import com.financies.financiesapi.model.entities.User;
import com.financies.financiesapi.repositories.TypeExpenseRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TypeExpenseService {

	private final TypeExpenseRepository typeExpenseRepository;
	
	private final UserService userService;

	public void create(TypeExpenseDTO typeExpenseDTO) {

		var userLogged = userService.getUserLogged();

		if (existsByDescription(typeExpenseDTO.getDescription(), userLogged)) {
			throw new BusinessException("Type of expense already exist");
		}
		var typeExpense = TypeExpense.builder().id(null).description(typeExpenseDTO.getDescription()).user(userLogged)
				.build();

		typeExpenseRepository.save(typeExpense);
	}

	public Page<TypeExpenseDTO> getAllPageable(Pageable pageable) {

		var list = typeExpenseRepository.findAllByUser(userService.getUserLogged(), pageable);

		return TypeExpenseMapper.INSTANCE.pageEntityToPageDTO(list);
	}
	
	public List<TypeExpenseDTO> getAll() {

		var list = typeExpenseRepository.findAllByUser(userService.getUserLogged());

		return TypeExpenseMapper.INSTANCE.listEntityToListDTO(list);

	}

	public TypeExpenseDTO getById(Integer id) {

		var type = typeExpenseRepository.findByIdAndUser(id, userService.getUserLogged());

		if (type == null) {
			throw new BusinessException("You cannot access another user's expense type");
		}
		return TypeExpenseMapper.INSTANCE.entityToDTO(type);
	}

	public void update(TypeExpenseDTO typeExpenseDTO) {

		var typeExpense = typeExpenseRepository.findById(typeExpenseDTO.getId());

		if (existsByDescription(typeExpenseDTO.getDescription(), userService.getUserLogged())) {
			throw new BusinessException("Type of Expense already exist and for this you cannot update this type");
		}

		typeExpense.get().setDescription(typeExpenseDTO.getDescription());
		typeExpenseRepository.save(typeExpense.get());
	}

	public void delete(Integer id) {

		if (!existsByIdAndUser(id, userService.getUserLogged())) {
			throw new BusinessException("Type doesn't exist and for this cannot be deleted");
		}

		typeExpenseRepository.deleteById(id);
	}

//	

	private boolean existsByIdAndUser(Integer id, User user) {
		return typeExpenseRepository.existsByIdAndUser(id, user);
	}

	private boolean existsByDescription(String description, User user) {
		return typeExpenseRepository.existsByDescriptionIgnoreCaseAndUser(description, user);
	}

}
