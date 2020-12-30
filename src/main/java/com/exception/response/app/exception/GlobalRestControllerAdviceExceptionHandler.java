package com.exception.response.app.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class GlobalRestControllerAdviceExceptionHandler {
	@ExceptionHandler(UserEmailNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public CustomeErrorDetails emailNotFound(UserEmailNotFoundException ex) {
		return new CustomeErrorDetails(new Date(), "From @RestControllerAdvice NOT FOUND", ex.getMessage());
	}

}
