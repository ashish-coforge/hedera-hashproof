/**
 * 
 */
package com.hedera.hashproof.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hedera.hashproof.HederaHashproofApplicationTests;

/**
 * Integration test for <code>HashProofAccountController</code>
 *
 */
@WebAppConfiguration
public class HashProofAccountControllerIT extends HederaHashproofApplicationTests {
	
private MockMvc mvc;
	
	/**
	 * instance for web ApplicationContext
	 */
	@Autowired
	private WebApplicationContext webAppContext;
	
	/**
	 * Initial configurations for running test integration test cases. 
	 */
	@BeforeEach
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}
	
	/**
	 * Integration test case to test the createAccount functionality.
	 * @throws Exception whenever any error condition occurs
	 */
	@Test
	public void testCreateAccount() throws Exception {
		String uri = "/hashproof/registration";
		String inputJson = this.objectToJson("Y");
		
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.param("writeData", "Y")
				.content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	/**
	 * Method to convert object to JSON representation.
	 * @param obj, not null
	 * @return JSON representation of java Object
	 * @throws JsonProcessingException whenever any issue occurs during mapping objects
	 */
	private String objectToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objMapper = new ObjectMapper();
		return objMapper.writeValueAsString(obj);
	}

}
