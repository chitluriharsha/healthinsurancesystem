package com.poc.healthinsurancesystem.service;

import static org.mockito.Mockito.doNothing;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.poc.healthinsurancesystem.model.DependentDetails;
import com.poc.healthinsurancesystem.model.DependentDetailsRequest;
import com.poc.healthinsurancesystem.model.EmployeeInfoModel;
import com.poc.healthinsurancesystem.repository.EmployeeInsuranceDetailsRepository;
import com.poc.healthinsurancesystem.util.Util;

import freemarker.template.TemplateException;

@ExtendWith(SpringExtension.class)
class HealthInsuranceServiceTest {

	@InjectMocks
	private HealthInsuranceService healthInsuranceService;

	@Mock
	private Util util;

	@Mock
	private EmployeeInsuranceDetailsRepository employeeInsuranceDetailsRepository;

	@Test
	void newEmployeeCreationTest() throws MessagingException, IOException, TemplateException {
		EmployeeInfoModel employeeInfo = new EmployeeInfoModel();
		employeeInfo.setName("test name");
		doNothing().when(util).sendMail(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(),
				ArgumentMatchers.anyString(), ArgumentMatchers.anyMap(), ArgumentMatchers.anyString());

		healthInsuranceService.newEmployeeCreation(employeeInfo);
	}

	@Test
	void deactiveEmployeeTest() throws MessagingException, IOException, TemplateException {
		doNothing().when(employeeInsuranceDetailsRepository)
				.updateInsuranceDateOfDeaactivation(ArgumentMatchers.any(Date.class), ArgumentMatchers.anyString());
		doNothing().when(util).sendMail(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(),
				ArgumentMatchers.anyString(), ArgumentMatchers.anyMap(), ArgumentMatchers.anyString());
		healthInsuranceService.deactiveEmployee(new EmployeeInfoModel());
	}

	@Test
	void saveAndNotifyDependentDetailsTest() throws MessagingException, IOException, TemplateException {
		DependentDetailsRequest dependentDetailsRequest = new DependentDetailsRequest();
		dependentDetailsRequest.setEmployeeId("E1IN00000");

		List<DependentDetails> dependentDetailsList = new ArrayList<>();
		DependentDetails dependentDetails = new DependentDetails();
		dependentDetails.setName("Test name");
		dependentDetails.setDateOfBirth(new Date(new java.util.Date().getTime()));
		dependentDetails.setRelation("test relation");
		
		dependentDetailsList.add(dependentDetails);
		dependentDetailsRequest.setDependentDetails(dependentDetailsList);;
		//doNothing().when(employeeInsuranceDetailsRepository).saveAll(ArgumentMatchers.anyList());
		doNothing().when(util).sendMail(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(),
				ArgumentMatchers.anyString(), ArgumentMatchers.anyMap(), ArgumentMatchers.anyString());
		healthInsuranceService.saveAndNotifyDependentDetails(dependentDetailsRequest);
	}

}
