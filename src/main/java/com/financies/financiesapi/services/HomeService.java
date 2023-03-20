package com.financies.financiesapi.services;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.financies.financiesapi.model.dtos.CurrentBalanceDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HomeService {

	private final IncomeService incomeService;

	public CurrentBalanceDTO getCurrentBalance() {

		var currentBalance = CurrentBalanceDTO.builder().dateActual(LocalDate.now())
				.valueIncome(incomeService.getCurrentIncome()).build();

		return currentBalance;

	}

}
