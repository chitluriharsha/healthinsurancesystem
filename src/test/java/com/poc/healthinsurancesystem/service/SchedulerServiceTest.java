package com.poc.healthinsurancesystem.service;

import static org.mockito.Mockito.doNothing;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.poc.healthinsurancesystem.repository.EmployeeInsuranceDetailsRepository;

@ExtendWith(SpringExtension.class)
class SchedulerServiceTest {
	
	@InjectMocks
	private SchedulerService schedulerService;
	
	@Mock
	private EmployeeInsuranceDetailsRepository employeeInsuranceDetailsRepository;
	
	@Test
	void deleteExpiredRecordsTest() {
		doNothing().when(employeeInsuranceDetailsRepository).deleteExpiredRecords(new Date(new java.util.Date().getTime()));
		schedulerService.deleteExpiredRecords();
	}

}
