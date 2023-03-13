package com.financies.financiesapi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.financies.financiesapi.generics.GenericMapper;
import com.financies.financiesapi.model.dtos.UserNamePhotoDTO;
import com.financies.financiesapi.model.entities.User;

@Mapper
public interface UserNamePhotoMapper extends GenericMapper<User, UserNamePhotoDTO> {

	UserNamePhotoMapper INSTANCE = Mappers.getMapper(UserNamePhotoMapper.class);

}
