package com.poc.healthinsurancesystem.repository;

import java.sql.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poc.healthinsurancesystem.domain.EmployeeInsuranceDetails;

@Transactional
@Repository
public interface EmployeeInsuranceDetailsRepository extends JpaRepository<EmployeeInsuranceDetails, Integer> {

	@Modifying
	@Query(value = "update employeeinsurance.employee_insurance_details e set e.date_of_deactivation = :date where e.employee_id = :employeeId", nativeQuery = true)
	void updateInsuranceDateOfDeaactivation(Date date, String employeeId);
	
	@Modifying
	@Query(value = "delete from employeeinsurance.employee_insurance_details where date_of_deactivation < :date", nativeQuery = true)
	void deleteExpiredRecords(Date date);

}
