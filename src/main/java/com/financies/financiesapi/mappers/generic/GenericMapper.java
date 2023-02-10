package com.financies.financiesapi.mappers.generic;

import java.util.List;

public interface GenericMapper<E, DTO> {

	DTO entityToDTO(E entity);

	E DTOToEntity(DTO dto);

	List<DTO> listEntityToListDTO(List<E> lista);

}