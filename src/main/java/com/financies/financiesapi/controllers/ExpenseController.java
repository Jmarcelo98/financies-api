package com.financies.financiesapi.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financies.financiesapi.model.dtos.ExpenseDTO;
import com.financies.financiesapi.model.dtos.input.ExpenseFilterDTO;
import com.financies.financiesapi.services.ExpenseService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@RequestMapping("/expense")
public class ExpenseController {
	
	private final ExpenseService expenseService;
	
	@PostMapping("/filter/pageable")
	public ResponseEntity<Page<ExpenseDTO>> getAllByFilter(@RequestBody ExpenseFilterDTO expenseFilterDTO,
			Pageable pageable) {
		return ResponseEntity.ok(expenseService.getAllByFilter(expenseFilterDTO, pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ExpenseDTO> getById(@PathVariable Integer id) {
		return ResponseEntity.ok(expenseService.getById(id));
	}

	@PatchMapping
	public ResponseEntity<Void> update(@RequestBody @Validated ExpenseDTO expenseDTO) {
		expenseService.update(expenseDTO);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		expenseService.delete(id);
		return ResponseEntity.ok().build();
	}

}
