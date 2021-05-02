package com.poc.healthinsurancesystem.model;

import java.sql.Date;

import lombok.Data;

@Data
public class DependentDetails {
	
	private String name;
	private Date dateOfBirth;
	private String relation;

}
