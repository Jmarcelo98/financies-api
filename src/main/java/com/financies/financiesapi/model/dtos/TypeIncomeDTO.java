package com.financies.financiesapi.model.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeIncomeDTO {

	private Integer id;

	@NotNull(message = "{type.income.description.not.null}")
	@NotBlank(message = "{type.income.description.not.blank}")
	private String description;

}
