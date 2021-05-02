package com.poc.healthinsurancesystem.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.poc.healthinsurancesystem.constants.HealthInsuranceSystemConstants;
import com.poc.healthinsurancesystem.domain.EmployeeInsuranceDetails;
import com.poc.healthinsurancesystem.model.DependentDetails;
import com.poc.healthinsurancesystem.model.DependentDetailsRequest;
import com.poc.healthinsurancesystem.model.EmployeeInfoModel;
import com.poc.healthinsurancesystem.repository.EmployeeInsuranceDetailsRepository;
import com.poc.healthinsurancesystem.util.Util;

import freemarker.template.TemplateException;

@Component
public class HealthInsuranceService {
	private static Logger logger = LoggerFactory.getLogger(HealthInsuranceService.class);

	@Autowired
	private Util util;

	@Value("${insurance.emailId}")
	private String insuranceEmailId;

	@Autowired
	private EmployeeInsuranceDetailsRepository employeeInsuranceDetailsRepository;

	public void newEmployeeCreation(EmployeeInfoModel employeeInfo)
			throws MessagingException, IOException, TemplateException {
		logger.info("HealthInsuranceService :: newEmployeeCreation request: {}", employeeInfo);
		logger.info("saving the new employee information to DB");
		EmployeeInsuranceDetails employeeInsuranceDetails = new EmployeeInsuranceDetails();
		employeeInsuranceDetails.setEmployeeId(employeeInfo.getEmployeeId());
		employeeInsuranceDetails.setName(employeeInfo.getName());
		employeeInsuranceDetails.setDateOfBirth(employeeInfo.getDateOfBirth());
		employeeInsuranceDetails.setRelationWithEmployee(HealthInsuranceSystemConstants.SELF);
		employeeInsuranceDetails.setDateOfActivation(util.addSevenDaysToCurrentdate());
		employeeInsuranceDetailsRepository.save(employeeInsuranceDetails);
		logger.info("saved the new employee information to DB");

		logger.info("Sending email to the employee as a new joining for the dependent details of the insurance.");
		Map<String, Object> employeeEmailMap = new HashMap<>();
		employeeEmailMap.put("employeeName", employeeInfo.getName());
		util.sendMail(employeeInfo.getEmailId(), "Dependent details - Required",
				HealthInsuranceSystemConstants.NEW_EMPLOYEE_DEPENDANT_DETAILS_EMAIL_TEXT, employeeEmailMap,
				"DependentDetailsTemplate");

		logger.info(
				"Email have been sent to the employee as a new joining for the dependent details of the insurance.");

		logger.info("Sending email to the insurance company as a new joining");
		Map<String, Object> insuranceEmailMap = new HashMap<>();
		insuranceEmailMap.put("employeeData", employeeInfo);
		util.sendMail(insuranceEmailId, "Addition of New Employee",
				HealthInsuranceSystemConstants.NEW_EMPLOYEE_INSURANCE_EMAIL_TEXT, insuranceEmailMap,
				"InsuranceAddNewEmployeeTemplate");
		logger.info("Email have been sent to the insurance company as a new joining.");

	}

	public void deactiveEmployee(EmployeeInfoModel employeeInfo)
			throws MessagingException, IOException, TemplateException {
		logger.info("updating the insurance deactivation date for the employee : {}", employeeInfo.getEmployeeId());
		employeeInsuranceDetailsRepository.updateInsuranceDateOfDeaactivation(util.addSevenDaysToCurrentdate(),
				employeeInfo.getEmployeeId());
		logger.info("updated the insurance deactivation date for the employee");
		logger.info("Sending email to the insurance company to deactivate the employee from health insurance.");
		Map<String, Object> insuranceEmailMap = new HashMap<>();
		insuranceEmailMap.put("employeeData", employeeInfo);
		util.sendMail(insuranceEmailId, "Deactive an employee", HealthInsuranceSystemConstants.DEACTIVATE_EMPLOYEE,
				insuranceEmailMap, "InsuranceRemoveEmployeeTemplate");
		logger.info("Email have been sent to the insurance company to deactivate the employee from health insurance.");
	}

	public void saveAndNotifyDependentDetails(DependentDetailsRequest dependentDetails)
			throws MessagingException, IOException, TemplateException {

		String employeeId = dependentDetails.getEmployeeId();
		List<DependentDetails> dependentDetailsList = dependentDetails.getDependentDetails();
		logger.info("saving the dependent details to the database");
		List<EmployeeInsuranceDetails> employeeInsuranceDetails = dependentDetailsList.stream().filter(Objects::nonNull)
				.map(dependentDetail -> {
					EmployeeInsuranceDetails employeeInsuranceDetail = new EmployeeInsuranceDetails();
					employeeInsuranceDetail.setDateOfBirth(dependentDetail.getDateOfBirth());
					employeeInsuranceDetail.setName(dependentDetail.getName());
					employeeInsuranceDetail.setEmployeeId(employeeId);
					employeeInsuranceDetail.setRelationWithEmployee(dependentDetail.getRelation());
					employeeInsuranceDetail.setDateOfActivation(util.addSevenDaysToCurrentdate());
					return employeeInsuranceDetail;
				}).collect(Collectors.toList());
		employeeInsuranceDetailsRepository.saveAll(employeeInsuranceDetails);
		logger.info("saved the dependent details to the database");
		logger.info("Sending email to the insurance company to intimate dependents are added to the employeeId: {}",
				dependentDetails.getEmployeeId());
		Map<String, Object> insuranceEmailMap = new HashMap<>();
		insuranceEmailMap.put("dependentDetailsRequest", dependentDetails);
		util.sendMail(insuranceEmailId, "Dependents are added to an employer",
				HealthInsuranceSystemConstants.ADD_DEPENDENTS, insuranceEmailMap,
				"InsuranceEmployeeDependantDetailsUpdateTemplate");
		logger.info("Email have been sent to the insurance company to intimate dependents are added.");

	}

}
