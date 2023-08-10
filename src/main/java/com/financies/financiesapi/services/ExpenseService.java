package com.financies.financiesapi.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.financies.financiesapi.handlers.BusinessException;
import com.financies.financiesapi.handlers.ResourceNotFoundException;
import com.financies.financiesapi.mappers.ExpenseMapper;
import com.financies.financiesapi.model.dtos.ExpenseDTO;
import com.financies.financiesapi.model.dtos.input.ExpenseFilterDTO;
import com.financies.financiesapi.model.entities.User;
import com.financies.financiesapi.repositories.ExpenseRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ExpenseService {

	private final ExpenseRepository expenseRepository;

	private final UserService userService;

	public Page<ExpenseDTO> getAllByFilter(ExpenseFilterDTO expenseFilterDTO, Pageable pageable) {

		var list = expenseRepository.findAllByFilterAndPageable(userService.getUserLogged().getId(),
				expenseFilterDTO.getIsReceived(),
				expenseFilterDTO.getDateReference() != null ? expenseFilterDTO.getDateReference().getYear() : null,
				expenseFilterDTO.getDateReference() != null ? expenseFilterDTO.getDateReference().getMonth().getValue()
						: null,
				expenseFilterDTO.getTypeExpenseDTO() != null ? expenseFilterDTO.getTypeExpenseDTO().getId() : null,
				pageable);

		return ExpenseMapper.INSTANCE.pageEntityToPageDTO(list);
	}

	public Double getCurrentExpense() {

		return expenseRepository.getCurrentExpense(userService.getUserLogged().getId()).orElse(0.0);

	}

	public ExpenseDTO getById(Integer id) {

		var type = expenseRepository.findByIdAndUser(id, userService.getUserLogged());

		if (type == null) {
			throw new BusinessException("You cannot access another user's expense");
		}

		return ExpenseMapper.INSTANCE.entityToDTO(type);

	}

	public void update(ExpenseDTO expenseDTO) {

		expenseRepository.findById(expenseDTO.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Expense not found by id"));

		var expense = ExpenseMapper.INSTANCE.DTOToEntity(expenseDTO);

		expense.setUser(userService.getUserLogged());

		expenseRepository.save(expense);

	}

	public void delete(Integer id) {

		if (!existsByIdAndUser(id, userService.getUserLogged())) {
			throw new BusinessException("Impossible to delete another user's expense");
		}

		expenseRepository.deleteById(id);

	}

	public List<ExpenseDTO> getLastFourIncome() {

		return ExpenseMapper.INSTANCE.listEntityToListDTO(
				expenseRepository.findTop4ByUserAndIsReceivedOrderByDateReferenceDesc(userService.getUserLogged(), true));

	}

	private boolean existsByIdAndUser(Integer id, User user) {
		return expenseRepository.existsByIdAndUser(id, user);
	}

}
