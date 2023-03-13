package com.financies.financiesapi.services;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.financies.financiesapi.model.entities.Expense;
import com.financies.financiesapi.model.entities.Income;
import com.financies.financiesapi.model.entities.TypeExpense;
import com.financies.financiesapi.model.entities.TypeIncome;
import com.financies.financiesapi.model.entities.User;
import com.financies.financiesapi.repositories.ExpenseRepository;
import com.financies.financiesapi.repositories.IncomeRepository;
import com.financies.financiesapi.repositories.TypeExpenseRepository;
import com.financies.financiesapi.repositories.TypeIncomeRepository;
import com.financies.financiesapi.repositories.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class DBService {

	private final TypeExpenseRepository typeExpenseRepository;
	private final TypeIncomeRepository typeIncomeRepository;
	private final IncomeRepository incomeRepository;
	private final ExpenseRepository expenseRepository;
	private final UserRepository userRepository;

	public void createdBDH2() {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		var localDateUser1 = LocalDate.of(2021, 4, 16);
		var localDateUser2 = LocalDate.of(2022, 8, 1);
		var localDateUserBirth1 = LocalDate.of(1998, 11, 16);
		var localDateUserBirth2 = LocalDate.of(1999, 5, 26);

		var user1 = User.builder().id(null).name("João Marcelo").dateCreation(localDateUser1)
				.dateBirth(localDateUserBirth1).photo(null).email("joao@gmail.com").password(encoder.encode("1234"))
				.build();

		var user2 = User.builder().id(null).name("Marcos Giovanny").dateCreation(localDateUser2)
				.dateBirth(localDateUserBirth2).photo(null).email("marcos@gmail.com").password(encoder.encode("12345"))
				.build();

		userRepository.saveAll(Arrays.asList(user1, user2));

		var typeExpense1User1 = TypeExpense.builder().id(null).description("Spotify").user(user1).build();
		var typeExpense2User1 = TypeExpense.builder().id(null).description("Rental").user(user1).build();

		var typeExpense1User2 = TypeExpense.builder().id(null).description("Transport").user(user2).build();
		var typeExpense2User2 = TypeExpense.builder().id(null).description("Food").user(user2).build();

		typeExpenseRepository
				.saveAll(Arrays.asList(typeExpense1User1, typeExpense2User1, typeExpense1User2, typeExpense2User2));

		var typeIncome1User1 = TypeIncome.builder().id(null).description("Salary").user(user1).build();
		var typeIncome2User1 = TypeIncome.builder().id(null).description("Bet").user(user1).build();
		var typeIncome3User1 = TypeIncome.builder().id(null).description("Projects/Freelance").user(user1).build();

		var typeIncome1User2 = TypeIncome.builder().id(null).description("Sale of food").user(user2).build();
		var typeIncome2User2 = TypeIncome.builder().id(null).description("Salary").user(user2).build();

		typeIncomeRepository.saveAll(Arrays.asList(typeIncome1User1, typeIncome2User1, typeIncome3User1,
				typeIncome1User2, typeIncome2User2));

		var incomeDate1User1 = LocalDate.of(2023, 1, 2);
		var incomeDate2User1 = LocalDate.of(2023, 2, 1);
		var incomeDate3User1 = LocalDate.of(2023, 2, 13);
		var incomeDate4User1 = LocalDate.of(2022, 12, 13);

		var income1User1 = Income.builder().id(null).description(null).value(6200.00).dateInclusion(incomeDate1User1)
				.isReceived(true).typeIncome(typeIncome1User1).user(user1).build();

		var income2User1 = Income.builder().id(null).description(null).value(6900.50).dateInclusion(incomeDate2User1)
				.isReceived(true).typeIncome(typeIncome1User1).user(user1).build();

		var income3User1 = Income.builder().id(null).description("Cruzeiro win against Atlético-MG").value(60.00)
				.dateInclusion(incomeDate3User1).isReceived(false).dateReference(null).typeIncome(typeIncome2User1)
				.user(user1).build();

		var income4User1 = Income.builder().id(null).description("Project PJ").value(1060.00)
				.dateInclusion(incomeDate3User1).isReceived(true).dateReference(incomeDate3User1)
				.typeIncome(typeIncome3User1).user(user1).build();

		var income5User1 = Income.builder().id(null).description("Freelancer").value(360.00)
				.dateInclusion(incomeDate4User1).isReceived(true).dateReference(incomeDate4User1)
				.typeIncome(typeIncome3User1).user(user1).build();

		var income6User1 = Income.builder().id(null).description("Freelancer GOV").value(3360.00)
				.dateInclusion(LocalDate.now()).isReceived(true).dateReference(LocalDate.now())
				.typeIncome(typeIncome3User1).user(user1).build();

		var incomeDate1User2 = LocalDate.of(2023, 1, 16);
		var incomeDate2User2 = LocalDate.of(2023, 1, 31);

		var income1User2 = Income.builder().id(null).description("First installment").value(1000.00)
				.dateInclusion(incomeDate1User2).isReceived(true).typeIncome(typeIncome2User2).user(user2).build();

		var income2User2 = Income.builder().id(null).description("Second installment").value(500.50)
				.dateInclusion(incomeDate2User2).isReceived(false).typeIncome(typeIncome2User2).user(user2).build();

		incomeRepository.saveAll(Arrays.asList(income1User1, income2User1, income3User1, income4User1, income5User1,
				income6User1, income1User2, income2User2));

		var expenseDate1User1 = LocalDate.of(2023, 2, 1);

		var expense1User1 = Expense.builder().id(null).description(null).value(39.90).dateInclusion(expenseDate1User1)
				.isReceived(true).typeExpense(typeExpense1User1).dateReference(expenseDate1User1).user(user1).build();

		var expense2User1 = Expense.builder().id(null).description(null).value(2000.00).dateInclusion(incomeDate1User1)
				.isReceived(true).dateReference(incomeDate1User1).typeExpense(typeExpense2User1).user(user1).build();

		var expenseDate2User1 = LocalDate.of(2023, 2, 10);

		var expense1User2 = Expense.builder().id(null).description("Uber to home").value(35.90)
				.dateInclusion(LocalDate.now()).isReceived(true).dateReference(LocalDate.now()).typeExpense(typeExpense1User2).user(user2).build();

		var expense2User2 = Expense.builder().id(null).description("Lunch").value(20.00)
				.dateInclusion(expenseDate2User1).isReceived(false).dateReference(null).typeExpense(typeExpense2User2).user(user2).build();

		expenseRepository.saveAll(Arrays.asList(expense1User1, expense2User1, expense1User2, expense2User2));
	}

}
