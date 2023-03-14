package com.financies.financiesapi.model.dtos.input;

import java.time.LocalDate;

import com.financies.financiesapi.model.dtos.TypeIncomeDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeFilterDTO {

	private Boolean isReceived;

	private LocalDate dateReference;

	private TypeIncomeDTO typeIncome;

}
