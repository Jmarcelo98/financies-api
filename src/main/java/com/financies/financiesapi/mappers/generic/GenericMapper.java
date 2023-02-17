package com.financies.financiesapi.mappers.generic;

import java.util.List;

import org.springframework.data.domain.Page;

public interface GenericMapper<E, DTO> {

	DTO entityToDTO(E entity);

	E DTOToEntity(DTO dto);

	List<DTO> listEntityToListDTO(List<E> lista);
	
	default Page<DTO> pageEntityToPageDTO(Page<E> pageEntity) {
		return pageEntity.map(this::entityToDTO);
	}

}