package com.financies.financiesapi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.financies.financiesapi.mappers.generic.GenericMapper;
import com.financies.financiesapi.model.dtos.UserDTO;
import com.financies.financiesapi.model.entities.User;

@Mapper
public interface UserMapper extends GenericMapper<User, UserDTO> {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

}
