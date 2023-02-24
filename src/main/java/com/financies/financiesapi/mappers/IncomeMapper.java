package com.financies.financiesapi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.financies.financiesapi.mappers.generic.GenericMapper;
import com.financies.financiesapi.model.dtos.IncomeDTO;
import com.financies.financiesapi.model.entities.Income;

@Mapper
public interface IncomeMapper extends GenericMapper<Income, IncomeDTO> {

	IncomeMapper INSTANCE = Mappers.getMapper(IncomeMapper.class);

}
