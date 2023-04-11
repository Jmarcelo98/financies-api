package com.financies.financiesapi.model.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeExpenseDTO {

	private Integer id;
	
	@NotNull(message = "{type.expense.description.not.null}")
	@NotBlank(message = "{type.expense.description.not.blank}")
	private String description;

}
