package com.epam.incubation.service.hotelinfo.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.epam.incubation.service.hotelinfo.datamodel.ApiError;
import com.epam.incubation.service.hotelinfo.exception.RecordNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected ResponseEntity<ApiError> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
		String error = "Malformed JSON request";
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> globalExceptionHandling(Exception ex) {
		ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(RecordNotFoundException.class)
	protected ResponseEntity<ApiError> handleEntityNotFound(RecordNotFoundException ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
		ApiError error = new ApiError(HttpStatus.FORBIDDEN, ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<ApiError> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

	return buildResponseEntity(
				new ApiError(HttpStatus.BAD_REQUEST, "Validation Error", ex.getFieldErrors()));
	}
	
	private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
}
