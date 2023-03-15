package com.financies.financiesapi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.financies.financiesapi.generics.GenericMapper;
import com.financies.financiesapi.model.dtos.ExpenseDTO;
import com.financies.financiesapi.model.entities.Expense;

@Mapper
public interface ExpenseMapper extends GenericMapper<Expense, ExpenseDTO> {
	
	ExpenseMapper INSTANCE = Mappers.getMapper(ExpenseMapper.class);

}
