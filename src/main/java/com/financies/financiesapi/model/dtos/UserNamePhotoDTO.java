package com.financies.financiesapi.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserNamePhotoDTO {

	private String name;

	private Byte[] photo;

}
