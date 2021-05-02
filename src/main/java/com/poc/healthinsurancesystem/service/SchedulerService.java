package com.poc.healthinsurancesystem.service;

import java.sql.Date;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.poc.healthinsurancesystem.repository.EmployeeInsuranceDetailsRepository;

@Component
public class SchedulerService {

	private static Logger logger = LoggerFactory.getLogger(SchedulerService.class);

	@Autowired
	private EmployeeInsuranceDetailsRepository employeeInsuranceDetailsRepository;

	@Scheduled(cron = "0 0 1 * * *")
	public void deleteExpiredRecords() {
		logger.info("Intiating the schedular to delete the expired the records from the employee insurance table");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new java.util.Date());
		Date sqlDate = new java.sql.Date(cal.getTime().getTime());
		logger.info("starting the call to delete the expired the records from the employee insurance table");
		employeeInsuranceDetailsRepository.deleteExpiredRecords(sqlDate);
		logger.info("completed the call to delete the expired the records from the employee insurance table");
	}

}
