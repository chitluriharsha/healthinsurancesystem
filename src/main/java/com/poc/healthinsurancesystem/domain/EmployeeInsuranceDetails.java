package com.poc.healthinsurancesystem.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EmployeeInsuranceDetails {
	
	@Id
	@GeneratedValue
	private int id;
	private String employeeId;
	private String name;
	private Date dateOfBirth;
	private String relationWithEmployee;
	private Date dateOfActivation;
	private Date dateOfDeactivation;

}
