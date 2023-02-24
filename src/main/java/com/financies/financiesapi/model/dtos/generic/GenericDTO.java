package com.financies.financiesapi.model.dtos.generic;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericDTO {

	private Integer id;

	private String description;

	private Double value;

	private LocalDate dateInclusion;

	private Boolean isReceived;
}
