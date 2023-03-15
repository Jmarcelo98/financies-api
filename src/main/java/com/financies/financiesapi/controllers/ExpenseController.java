package com.financies.financiesapi.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financies.financiesapi.model.dtos.ExpenseDTO;
import com.financies.financiesapi.services.ExpenseService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@RequestMapping("/expense")
public class ExpenseController {
	
	private final ExpenseService expenseService;
	
	@GetMapping
	public ResponseEntity<Page<ExpenseDTO>> getAll (Pageable pageable){
		return ResponseEntity.ok(expenseService.getAll(pageable));
	}

}
