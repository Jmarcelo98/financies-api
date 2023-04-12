package com.financies.financiesapi.model.dtos.input;

import java.time.LocalDate;

import com.financies.financiesapi.model.dtos.TypeExpenseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseFilterDTO {
	
	private Boolean isReceived;

	private LocalDate dateReference;

	private TypeExpenseDTO typeExpenseDTO;

}
