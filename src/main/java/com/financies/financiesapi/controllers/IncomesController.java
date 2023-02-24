package com.financies.financiesapi.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financies.financiesapi.model.dtos.IncomeDTO;
import com.financies.financiesapi.services.IncomeService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/income")
public class IncomesController {

	private final IncomeService incomeService;

	@GetMapping
	public ResponseEntity<Page<IncomeDTO>> getAll(Pageable pageable) {
		return ResponseEntity.ok(incomeService.getAll(pageable));
	}

}
