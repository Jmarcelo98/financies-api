package com.financies.financiesapi.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financies.financiesapi.model.dtos.CurrentBalanceDTO;
import com.financies.financiesapi.model.dtos.ExpenseCategoryMonthlyDTO;
import com.financies.financiesapi.services.HomeService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/homes")
public class HomeController {

	private final HomeService homeService;

	@GetMapping("/current-balance")
	public ResponseEntity<CurrentBalanceDTO> getCurrentBalance() {
		return ResponseEntity.ok(homeService.getCurrentBalance());
	}

	@GetMapping("/monthly-expenses")
	public ResponseEntity<List<ExpenseCategoryMonthlyDTO>> getMonthlyExpenses() {
		return ResponseEntity.ok(homeService.getExpenseCategoryMonthly());
	}

}
