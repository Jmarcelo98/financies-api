package com.financies.financiesapi.model.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO {

	@NotNull(message = "{email.not.null}")
	@Email(message = "{email.invalid}")
	private String email;

	@NotNull(message = "{password.not.null}")
	@Size(min = 4, message = "{password.min.size}")
	private String password;

}
