package com.financies.financiesapi.model.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrentBalanceDTO {

	private Double valueIncome;

	private Double valueExpense;

	private LocalDate dateActual;

}
