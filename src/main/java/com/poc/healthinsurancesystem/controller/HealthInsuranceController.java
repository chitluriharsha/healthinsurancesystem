package com.poc.healthinsurancesystem.controller;

import java.io.IOException;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poc.healthinsurancesystem.model.DependentDetailsRequest;
import com.poc.healthinsurancesystem.model.Response;
import com.poc.healthinsurancesystem.service.HealthInsuranceService;

import freemarker.template.TemplateException;

@RestController
public class HealthInsuranceController {
	
	private static Logger logger = LoggerFactory.getLogger(HealthInsuranceController.class);
	
	@Autowired
	private HealthInsuranceService healthInsuranceService;
	
	@PostMapping("/saveAndNotifyDependentDetails")
	public ResponseEntity<Response> saveAndNotifyDependentDetails(@RequestBody DependentDetailsRequest dependentDetails) throws MessagingException, IOException, TemplateException {
		logger.info("HealthInsuranceController :: saveAndNotifyDependentDetails request: {}",dependentDetails);
		healthInsuranceService.saveAndNotifyDependentDetails(dependentDetails);
		Response response = new Response();
		response.setCode(HttpStatus.OK.value());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
