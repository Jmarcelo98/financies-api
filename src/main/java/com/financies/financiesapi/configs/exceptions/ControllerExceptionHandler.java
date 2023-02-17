package com.financies.financiesapi.configs.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.financies.financiesapi.handlers.BusinessException;
import com.financies.financiesapi.handlers.ResourceNotFoundException;
import com.financies.financiesapi.handlers.UnauthorizedLoginException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {

		var headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		var status = HttpStatus.NOT_FOUND;
		var body = new ResponseError();

		body.setCode(status.value());
		body.setDescription(ex.getMessage());
		return handleExceptionInternal(ex, body, headers, status, request);
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {

		var headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		var status = HttpStatus.BAD_REQUEST;
		var body = new ResponseError();

		body.setCode(status.value());
		body.setDescription(ex.getMessage());
		return handleExceptionInternal(ex, body, headers, status, request);
	}

	@ExceptionHandler(UnauthorizedLoginException.class)
	public ResponseEntity<Object> handleUnauthorizedLoginException(UnauthorizedLoginException ex, WebRequest request) {

		var headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		var status = HttpStatus.UNAUTHORIZED;
		var body = new ResponseError();

		body.setCode(status.value());
		body.setDescription(ex.getMessage());
		return handleExceptionInternal(ex, body, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		var error = new ResponseError(HttpStatus.BAD_REQUEST.value(), details.toString());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
