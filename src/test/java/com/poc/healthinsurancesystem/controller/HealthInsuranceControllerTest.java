package com.poc.healthinsurancesystem.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.gson.Gson;
import com.poc.healthinsurancesystem.model.DependentDetailsRequest;
import com.poc.healthinsurancesystem.service.HealthInsuranceService;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class HealthInsuranceControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private HealthInsuranceService healthInsuranceService;
	
	@Test
	void saveAndNotifyDependentDetailsTest() throws Exception {
		Gson gson = new Gson();
		DependentDetailsRequest dependentDetails = new DependentDetailsRequest();
		dependentDetails.setEmployeeId("E1IN00000");
		
		this.mockMvc
		.perform(MockMvcRequestBuilders.post("/saveAndNotifyDependentDetails").content(gson.toJson(dependentDetails))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
		
	}

}
