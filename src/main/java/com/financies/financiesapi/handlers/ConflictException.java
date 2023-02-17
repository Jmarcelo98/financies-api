package com.financies.financiesapi.handlers;

public class ConflictException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConflictException(String mensagem) {
		super(mensagem);
	}

}