package com.exception.response.app.exception;

import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomeErrorDetails customeErrorDetails = new CustomeErrorDetails(new Date(),
				"From MethodArgumentNotValid Exception in GEH", ex.getMessage());
		return new ResponseEntity<Object>(customeErrorDetails, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomeErrorDetails customeErrorDetails = new CustomeErrorDetails(new Date(),
				"From HttpRequestMethodNotSupportedException in GEH - Method Not allowed", ex.getMessage());
		return new ResponseEntity<Object>(customeErrorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserEmailNotFoundException.class)
	public final ResponseEntity<Object> UserEmailNotFoundException(UserEmailNotFoundException ex,
			WebRequest webRequest) {
		CustomeErrorDetails customeErrorDetails = new CustomeErrorDetails(new Date(), ex.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<Object>(customeErrorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> ConstraintViolationExceptionMethod(ConstraintViolationException ex,
			WebRequest webRequest) {
		CustomeErrorDetails customeErrorDetails = new CustomeErrorDetails(new Date(), ex.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<Object>(customeErrorDetails, HttpStatus.BAD_REQUEST);

	}

}
