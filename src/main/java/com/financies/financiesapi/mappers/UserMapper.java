package com.financies.financiesapi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.financies.financiesapi.generics.GenericMapper;
import com.financies.financiesapi.model.dtos.UserDTO;
import com.financies.financiesapi.model.entities.User;

@Mapper
public interface UserMapper extends GenericMapper<User, UserDTO> {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

}
