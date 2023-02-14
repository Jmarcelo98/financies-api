package com.financies.financiesapi.controllers;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/type-incomes")
public class TypeIncomesController {

	private final TypeIncomeService typeIncomeService;

	@GetMapping
	public ResponseEntity<List<TypeIncomeDTO>> getAll(Pageable pageable) {
		return ResponseEntity.ok(typeIncomeService.getAll(pageable));
	}

	@PatchMapping
	public ResponseEntity<Void> update(@RequestBody TypeIncomeDTO typeIncomeDTO) {
		typeIncomeService.update(typeIncomeDTO);
		return ResponseEntity.ok().build();
	}

	@PostMapping
	public ResponseEntity<Void> create(String description) {
		typeIncomeService.create(description);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		typeIncomeService.delete(id);
		return ResponseEntity.ok().build();
	}

}
