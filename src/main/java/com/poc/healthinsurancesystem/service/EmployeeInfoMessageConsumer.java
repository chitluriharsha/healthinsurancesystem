package com.poc.healthinsurancesystem.service;

import java.io.IOException;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.poc.healthinsurancesystem.constants.HealthInsuranceSystemConstants;
import com.poc.healthinsurancesystem.model.EmployeeInfoModel;

import freemarker.template.TemplateException;

@Component
public class EmployeeInfoMessageConsumer {

	private static Logger logger = LoggerFactory.getLogger(EmployeeInfoMessageConsumer.class);

	@Autowired
	private HealthInsuranceService healthInsuranceService;

	@JmsListener(destination = "employeeDetailsQueue")
	public void messageListener(String jmsMessage) throws MessagingException, IOException, TemplateException {
		logger.info("Message received, {}", jmsMessage);
		Gson gson = new Gson();
		EmployeeInfoModel employeeInfo = gson.fromJson(jmsMessage, EmployeeInfoModel.class);
		logger.info("Message received JmsMessage, {}", employeeInfo);
		saveDataAndSendEmail(employeeInfo);
	}

	public void saveDataAndSendEmail(EmployeeInfoModel employeeInfo) throws MessagingException, IOException, TemplateException {
		logger.info("EmployeeInfoMessageConsumer::saveDataAndSendEmail with request: {}", employeeInfo);
		if (HealthInsuranceSystemConstants.OPERATION_NEW_EMPLOYEE.equals(employeeInfo.getOperation())) {
			healthInsuranceService.newEmployeeCreation(employeeInfo);
		}

		if (HealthInsuranceSystemConstants.OPERATION_DEACTIVATE_EMPLOYEE.equals(employeeInfo.getOperation())) {
			healthInsuranceService.deactiveEmployee(employeeInfo);
		}

	}

}
