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

import com.financies.financiesapi.model.dtos.TypeIncomeDTO;
import com.financies.financiesapi.services.TypeIncomeService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/types-income")
public class TypeIncomesController {

	private final TypeIncomeService typeIncomeService;

	@GetMapping
	public ResponseEntity<Page<TypeIncomeDTO>> getAll(Pageable pageable) {
		return ResponseEntity.ok(typeIncomeService.getAll(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<TypeIncomeDTO> getById(@PathVariable Integer id) {
		return ResponseEntity.ok(typeIncomeService.getById(id));
	}

	@PatchMapping
	public ResponseEntity<Void> update(@RequestBody @Validated TypeIncomeDTO typeIncomeDTO) {
		typeIncomeService.update(typeIncomeDTO);
		return ResponseEntity.ok().build();
	}

	@PostMapping
	public ResponseEntity<Void> create(@RequestBody @Validated TypeIncomeDTO typeIncomeDTO) {
		typeIncomeService.create(typeIncomeDTO);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		typeIncomeService.delete(id);
		return ResponseEntity.ok().build();
	}

}
