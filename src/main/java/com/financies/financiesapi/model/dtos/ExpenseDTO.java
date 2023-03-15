package com.financies.financiesapi.model.dtos;

import com.financies.financiesapi.generics.GenericDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDTO extends GenericDTO {
	
	private TypeExpenseDTO typeExpenseDTO; 
}
