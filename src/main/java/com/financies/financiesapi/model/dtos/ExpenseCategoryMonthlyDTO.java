package com.financies.financiesapi.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseCategoryMonthlyDTO {

	private String descriptionTypeExpense;

	private Double valueExpense;

	private Integer items;

}
