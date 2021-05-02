package com.poc.healthinsurancesystem.service;

import java.io.IOException;

import javax.mail.MessagingException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.google.gson.Gson;
import com.poc.healthinsurancesystem.constants.HealthInsuranceSystemConstants;
import com.poc.healthinsurancesystem.model.EmployeeInfoModel;

import freemarker.template.TemplateException;

@ExtendWith(SpringExtension.class)
class EmployeeInfoMessageConsumerTest {
	
	@InjectMocks
	private EmployeeInfoMessageConsumer employeeInfoMessageConsumer;
	
	@Mock
	private HealthInsuranceService healthInsuranceService;
	
	@Test
	void messageListenerTest() throws MessagingException, IOException, TemplateException {
		EmployeeInfoModel employeeInfo = new EmployeeInfoModel();
		employeeInfo.setOperation(HealthInsuranceSystemConstants.OPERATION_NEW_EMPLOYEE);
		Gson gson = new Gson();
		employeeInfoMessageConsumer.messageListener(gson.toJson(employeeInfo));
	}
	
	@Test
	void messageListenerTestDeactivate() throws MessagingException, IOException, TemplateException {
		EmployeeInfoModel employeeInfo = new EmployeeInfoModel();
		employeeInfo.setOperation(HealthInsuranceSystemConstants.OPERATION_DEACTIVATE_EMPLOYEE);
		Gson gson = new Gson();
		employeeInfoMessageConsumer.messageListener(gson.toJson(employeeInfo));
	}

}
