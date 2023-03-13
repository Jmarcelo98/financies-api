package com.financies.financiesapi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.financies.financiesapi.generics.GenericMapper;
import com.financies.financiesapi.model.dtos.UserRegisterDTO;
import com.financies.financiesapi.model.entities.User;

@Mapper
public interface UserRegisterMapper extends GenericMapper<User, UserRegisterDTO> {

	UserRegisterMapper INSTANCE = Mappers.getMapper(UserRegisterMapper.class);

}
