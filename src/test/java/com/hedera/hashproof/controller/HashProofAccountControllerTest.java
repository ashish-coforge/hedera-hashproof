/**
 * 
 */
package com.hedera.hashproof.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import com.hedera.hashproof.HederaHashproofApplicationTests;
import com.hedera.hashproof.dto.HashProofAccountConfigResponse;
import com.hedera.hashproof.dto.Response;
import com.hedera.hashproof.exception.HashProofException;
import com.hedera.hashproof.exception.HashProofMessage;
import com.hedera.hashproof.service.HashProofAccountService;

/**
 *Test class to test <code>HashProofAccountController</code>.
 *
 */
public class HashProofAccountControllerTest extends HederaHashproofApplicationTests{

	/**
	 * Account service instance
	 */
	@Mock
	private HashProofAccountService hashProofAccountService;
	
	/**
	 * Account controller instance
	 */
	private HashProofAccountController accountController;

	/**
	 * Initial set up for running test cases for the <code>HashProofAccountController</code> class. 
	 */
	@BeforeEach
	public void setUp() {
		accountController = new HashProofAccountController();
		
		ReflectionTestUtils.setField(accountController, "hashProofAccountService", hashProofAccountService);
	}

		
	/**
	 * Test case to test the createAccount() API.
	 * @throws Exception whenever error condition occurs
	 */
	@Test
	public void testCreateAccountWithParam_Yes() throws Exception {
		HashProofAccountConfigResponse response = new HashProofAccountConfigResponse();
		response.setCustomerId("101011");
		response.setMessage(HashProofMessage.ACCOUNT_CREATION_SUCCESS.getDescription());
		response.setCode(HttpStatus.OK.value());
		
		String writeData = "Y";
		Mockito.when(hashProofAccountService.createAccount(writeData)).thenReturn(response);
				
		HashProofAccountConfigResponse response1 = (HashProofAccountConfigResponse) accountController.createAccount(writeData);
		
		int status = response1.getCode();
		assertEquals(200, status);
		assertEquals("101011", response1.getCustomerId());
	}
	
	/**
	 * Test case to test the createAccount() API when request parameter is 'No'.
	 * @throws Exception whenever error condition occurs
	 */
	@Test
	public void testCreateAccountWithParam_No() throws Exception {
		HashProofAccountConfigResponse response = new HashProofAccountConfigResponse();
		response.setCustomerId("101012");
		response.setMessage(HashProofMessage.ACCOUNT_CREATION_SUCCESS.getDescription());
		response.setCode(HttpStatus.OK.value());
		
		Mockito.when(hashProofAccountService.createAccount("N")).thenReturn(response);
		
		String writeData = "No";
		HashProofAccountConfigResponse response1 = (HashProofAccountConfigResponse) accountController.createAccount(writeData);
		
		int status = response1.getCode();
		assertEquals(200, status);
		assertEquals("101012", response1.getCustomerId());
	}
	
	/**
	 * Test case to test the createAccount() API when request parameter is invalid.
	 * @throws Exception whenever error condition occurs
	 */
	@Test
	public void testCreateAccountWithParam_Invalid() throws Exception {
			
		String writeData = "True";
		Response response = accountController.createAccount(writeData);
		   		
		int status = response.getCode();
		assertEquals(HttpStatus.BAD_REQUEST.value(), status);
		assertEquals(HashProofMessage.INVALID_INPUTS.getDescription(), response.getError().getDescription());
	}

	/**
	 * Test case to test the editAccount() API when request parameter is 'No'.
	 * @throws Exception whenever error condition occurs
	 */
	@Test
	public void testEditAccount() throws Exception {
		String customerId = "101013";
		String writeData = "No";
		Response response1 = accountController.editAccount(customerId, writeData);
		
		int status = response1.getCode();
		assertEquals(200, status);
	}
	
	/**
	 * Test case to test the editAccount() API when request parameter is null.
	 * @throws Exception whenever error condition occurs
	 */
	@Test
	public void testEditAccountWithParam_Null() throws Exception {
		String customerId = "101014";
		String writeData = null;
		Response response1 = accountController.editAccount(customerId, writeData);
		
		int status = response1.getCode();
		assertEquals(400, status);
	}
	
	/**
	 * Test case to test the removeAccount() API to verify success case.
	 * @throws Exception whenever error condition occurs
	 */
	@Test
	public void testRemoveAccount() throws Exception {
		String customerId = "101014";
		Mockito.when(hashProofAccountService.removeAccount(customerId)).thenReturn(1);
		
		Response response = accountController.removeAccount(customerId);
		
		int status = response.getCode();
		assertEquals(200, status);
	}
	
	/**
	 * Test case to test the removeAccount() API to verify failure case.
	 * @throws Exception whenever error condition occurs
	 */
	@Test
	public void testRemoveAccountWithInvalidReturn() throws Exception {
		String customerId = "101014";
		Mockito.when(hashProofAccountService.removeAccount(customerId)).thenReturn(0);
		
		Response response = accountController.removeAccount(customerId);
		
		int status = response.getCode();
		assertEquals(404, status);
	}
	
	/**
	 * Test case to test the removeAccount() API to verify failure case.
	 * @throws Exception whenever error condition occurs
	 */
	@Test
	public void testRemoveAccountWithException() throws Exception {
		String customerId = "101014";
		HashProofException exception = new HashProofException(HashProofMessage.TRANSACTION_FAIL.getDescription(),HttpStatus.BAD_REQUEST);
		Mockito.when(hashProofAccountService.removeAccount(customerId)).thenThrow(exception);
		
		Response response = accountController.removeAccount(customerId);
		
		int status = response.getCode();
		assertEquals(400, status);
	}
	
}
