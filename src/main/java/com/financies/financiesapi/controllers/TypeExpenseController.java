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

import com.financies.financiesapi.model.dtos.TypeExpenseDTO;
import com.financies.financiesapi.services.TypeExpenseService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/type-expenses")
public class TypeExpenseController {
	
	private final TypeExpenseService typeExpenseService;
	
	@PostMapping
	public ResponseEntity<Void> createTypeExpense(String description){
		typeExpenseService.createTypeExpense(description);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ResponseEntity<List<TypeExpenseDTO>> getAllTypeExpenses(Pageable pageable){
		return ResponseEntity.ok(typeExpenseService.getAll(pageable));
		
	}
	
	@PatchMapping
	public ResponseEntity<Void> updateTypeExpense(@RequestBody TypeExpenseDTO typeExpenseDTO){
		typeExpenseService.updateTypeExpense(typeExpenseDTO);
		
		return ResponseEntity.ok().build();
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTypeExpense(@PathVariable Integer id){
		typeExpenseService.deleteTypeExpense(id);
		return ResponseEntity.ok().build();
	}
	
	
	

}
