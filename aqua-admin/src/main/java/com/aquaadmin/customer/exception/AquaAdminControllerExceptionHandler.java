package com.aquaadmin.customer.exception;

import java.nio.file.AccessDeniedException;
import java.time.OffsetDateTime;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * @author chaitanyaarava
 *
 */
@RestControllerAdvice
public class AquaAdminControllerExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(AquaAdminControllerExceptionHandler.class);
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	ErrorMessage exceptionHandler( MethodArgumentTypeMismatchException exception) {
		LOGGER.error("Method argument type mismatch detected", exception);
		return new ErrorMessage(OffsetDateTime.now(), exception.getMessage());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	ErrorMessage exceptionHandler( ConstraintViolationException exception) {
		LOGGER.error("parameter constraint detected", exception);
		return new ErrorMessage(OffsetDateTime.now(), exception.getMessage());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	ErrorMessage exceptionHandler( IllegalArgumentException exception) {
		LOGGER.error("Illegel arguments detected", exception);
		return new ErrorMessage(OffsetDateTime.now(), exception.getMessage());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	ErrorMessage exceptionHandler( HttpMessageNotReadableException exception) {
		LOGGER.error("invalid body detected", exception);
		return new ErrorMessage(OffsetDateTime.now(), exception.getMessage());
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(CustomerNotFoundException.class)
	ErrorMessage exceptionHandler( CustomerNotFoundException exception) {
		LOGGER.error("customer not found", exception.getMessage());
		return new ErrorMessage(OffsetDateTime.now(), exception.getMessage());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(InternalServerError.class)
	ErrorMessage exceptionHandler( InternalServerError exception) {
		LOGGER.error("internal server error", exception);
		return new ErrorMessage(OffsetDateTime.now(), exception.getMessage());
	}
	
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(AccessDeniedException.class)
	ErrorMessage exceptionHandler( AccessDeniedException exception) {
		LOGGER.error("authorization error detected", exception);
		return new ErrorMessage(OffsetDateTime.now(), exception.getMessage());
	}
}
