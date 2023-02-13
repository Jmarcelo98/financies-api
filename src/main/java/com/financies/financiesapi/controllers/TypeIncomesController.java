package com.financies.financiesapi.controllers;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financies.financiesapi.model.dtos.TypeIncomeDTO;
import com.financies.financiesapi.services.TypeIncomeService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/type-incomes")
public class TypeIncomesController {

	private final TypeIncomeService typeIncomeService;

	@GetMapping
	public ResponseEntity<List<TypeIncomeDTO>> getAll(Pageable pageable) {
		return ResponseEntity.ok(typeIncomeService.getAll(pageable));
	}

}
