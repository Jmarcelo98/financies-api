package com.financies.financiesapi.services;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.financies.financiesapi.model.dtos.CurrentBalanceDTO;
import com.financies.financiesapi.model.dtos.LastTransactionDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HomeService {

	private final IncomeService incomeService;

	private final ExpenseService expenseService;

	public CurrentBalanceDTO getCurrentBalance() {

		var currentBalance = CurrentBalanceDTO.builder().dateActual(LocalDate.now())
				.valueIncome(incomeService.getCurrentIncome()).valueExpense(expenseService.getCurrentExpense()).build();

		return currentBalance;

	}

	public List<LastTransactionDTO> getLastTransactions() {

		var listIncome = incomeService.getLastFourIncome().stream()
				.map(a -> LastTransactionDTO.builder().dateReference(a.getDateReference())
						.description(a.getDescription()).value(a.getValue()).typeTransaction("Income").build())
				.collect(Collectors.toList());

		var listExpense = expenseService.getLastFourIncome().stream()
				.map(a -> LastTransactionDTO.builder().dateReference(a.getDateReference())
						.description(a.getDescription()).value(a.getValue()).typeTransaction("Expense").build())
				.collect(Collectors.toList());

		var listDTO = Stream.concat(listIncome.stream(), listExpense.stream()).collect(Collectors.toList());

		listDTO.sort(Comparator.comparing(LastTransactionDTO::getDateReference).reversed());

		return listDTO.stream().limit(4).collect(Collectors.toList());

	}

//	public List<ExpenseCategoryMonthlyDTO> getExpenseCategoryMonthly() {
//		
//		var teste = expenseService.getExpenseCategoryMonthly();
//		
//		for (int i = 0; i < teste.size(); i++) {
//			System.err.println(teste.get(i));
//			
//		}
//		
//		var b = splitted(teste);
//
//		for (int i = 0; i < b.length; i++) {
//			System.err.println("aqui: " + b[i]);
//		}
//
//		return null;
//
//	}
//
//	private String[] splitted(List<String> expenses) {
//		String splitted[] = null;
//
//		for (int i = 0; i < expenses.size(); i++) {
//			splitted = expenses.get(i).split(",");
//		}
//
//		return splitted;
//
//	}

}
