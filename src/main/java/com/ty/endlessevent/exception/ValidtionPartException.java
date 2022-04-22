package com.ty.endlessevent.exception;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class ValidtionPartException extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Map<String,String> errorlist = new LinkedHashMap<String,String>();
		List<ObjectError> errors = ex.getAllErrors();
		for(ObjectError error : errors) {
			String msg = error.getDefaultMessage();
			FieldError fieldError = (FieldError)error;
			errorlist.put(fieldError.getField(), msg);
			
		}
		
		return new ResponseEntity<Object>(errorlist, HttpStatus.BAD_REQUEST);
	}

}
