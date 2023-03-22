package com.financies.financiesapi.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.financies.financiesapi.model.dtos.CurrentBalanceDTO;
import com.financies.financiesapi.model.dtos.ExpenseCategoryMonthlyDTO;
import com.financies.financiesapi.model.entities.Expense;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HomeService {

	private final IncomeService incomeService;

	private final ExpenseService expenseService;

	private final TypeExpenseService typeExpenseService;

	public CurrentBalanceDTO getCurrentBalance() {

		var currentBalance = CurrentBalanceDTO.builder().dateActual(LocalDate.now())
				.valueIncome(incomeService.getCurrentIncome()).build();

		return currentBalance;

	}

	public List<ExpenseCategoryMonthlyDTO> getExpenseCategoryMonthly() {
		
		var teste = expenseService.getExpenseCategoryMonthly();
		
		for (int i = 0; i < teste.size(); i++) {
			System.err.println(teste.get(i));
			
		}
		
		var b = splitted(teste);

		for (int i = 0; i < b.length; i++) {
			System.err.println("aqui: " + b[i]);
		}

		return null;

	}

	private String[] splitted(List<String> expenses) {
		String splitted[] = null;

		for (int i = 0; i < expenses.size(); i++) {
			splitted = expenses.get(i).split(",");
		}

		return splitted;

	}

}
