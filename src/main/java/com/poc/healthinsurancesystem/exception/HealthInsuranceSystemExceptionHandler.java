package com.poc.healthinsurancesystem.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.poc.healthinsurancesystem.model.Response;

@ControllerAdvice
public class HealthInsuranceSystemExceptionHandler {

	private static Logger logger = LoggerFactory.getLogger(HealthInsuranceSystemExceptionHandler.class);

	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<Response> handleGenericException(Exception e) {
		logger.error(e.getMessage(), e);
		Response response = new Response();
		response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setMessage("Generic exception found");
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
