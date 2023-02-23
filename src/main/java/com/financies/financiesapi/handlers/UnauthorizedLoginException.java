package com.financies.financiesapi.handlers;

public class UnauthorizedLoginException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnauthorizedLoginException(String mensagem) {
		super(mensagem);
	}

}
