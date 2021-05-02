package com.poc.healthinsurancesystem.model;

import java.util.List;

import lombok.Data;

@Data
public class DependentDetailsRequest {
	
	private String employeeId;
	private List<DependentDetails> dependentDetails;

}
