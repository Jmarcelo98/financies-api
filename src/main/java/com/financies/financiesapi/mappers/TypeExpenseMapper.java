package com.financies.financiesapi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.financies.financiesapi.mappers.generic.GenericMapper;
import com.financies.financiesapi.model.dtos.TypeExpenseDTO;
import com.financies.financiesapi.model.entities.TypeExpense;

@Mapper
public interface TypeExpenseMapper extends GenericMapper<TypeExpense, TypeExpenseDTO> {

	TypeExpenseMapper INSTANCE = Mappers.getMapper(TypeExpenseMapper.class);

}
