package com.financies.financiesapi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.financies.financiesapi.generics.GenericMapper;
import com.financies.financiesapi.model.dtos.TypeIncomeDTO;
import com.financies.financiesapi.model.entities.TypeIncome;

@Mapper
public interface TypeIncomeMapper extends GenericMapper<TypeIncome, TypeIncomeDTO> {

	TypeIncomeMapper INSTANCE = Mappers.getMapper(TypeIncomeMapper.class);

}
