package com.financies.financiesapi.model.dtos;

import com.financies.financiesapi.model.dtos.generic.GenericDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeDTO extends GenericDTO {

	private TypeIncomeDTO typeIncome;

}
