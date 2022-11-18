/**
 * 
 */
package com.hedera.hashproof.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import com.hedera.hashproof.HederaHashproofApplicationTests;
import com.hedera.hashproof.dto.HashProofAccountConfigResponse;
import com.hedera.hashproof.exception.HashProofException;
import com.hedera.hashproof.model.HashProofCustomerConfigData;
import com.hedera.hashproof.repository.HashProofCustomerConfigRepository;


/**
 * Test class to test the <code>HashProofAccountService</code>.
 *
 */
public class HashProofAccountServiceTest extends HederaHashproofApplicationTests {
	
	/**
	 * JPA repository instance to store customer config data
	 */
	@Mock
	private HashProofCustomerConfigRepository customerRepository;

	/**
	 * Service instance responsible for processing customer data
	 */
	private HashProofAccountService accountService;
	
	/**
	 * Entity to map customer data
	 */
	private HashProofCustomerConfigData hashProofCustomerConfigData;
	

	/**
	 * Basic setup for running test cases.
	 */
	@BeforeEach
	void setUp() {
		this.accountService = new HashProofAccountService();
		ReflectionTestUtils.setField(accountService, "customerRepository", customerRepository);
			
	}

	/**
	 * Test case to verify and test the create account functionality.
	 * @throws Exception when create account end up with any exception conditions
	 */
	@Test
	public void testCreateAccount() throws Exception {
		HashProofAccountConfigResponse response = accountService.createAccount("Y");
		String customerId = response.getCustomerId();
		assertNotNull(customerId);
	}
	 
	
	/**
	 * Test case to verify and test the 
	 * @throws Exception whenever any exception condition occurs
	 */
	@Test
	public void testEditAccount() throws Exception {
		hashProofCustomerConfigData = new HashProofCustomerConfigData("1001001","Y","1001001", new Timestamp(System.currentTimeMillis()));
		Mockito.when(customerRepository.findByCustomerId("1001001")).thenReturn(hashProofCustomerConfigData);
		assertDoesNotThrow(() -> accountService.editAccount("1001001", "N"));

	}
	
	/**
	 * Test case to ascertain the exception condition during edit account.
	 * @throws Exception whenever any exception condition occurs
	 */
	@Test
	public void testEditAccount_throwsException() throws Exception {
		Mockito.when(customerRepository.findByCustomerId("1001001")).thenThrow(HashProofException.class);
		
		HashProofException thrown = assertThrows(
				HashProofException.class,
		           () -> accountService.editAccount("1001001", "N"));

		    assertTrue(thrown.getHttpStatus().getReasonPhrase().contains("Bad Request"));
		    assertTrue(thrown.getHttpStatus().value() == 400);
	}
	
	/**
	 * Test case to test the removeAccount functionality.
	 * @throws Exception whenever any exception condition occurs
	 */
	@Test
	public void testRemoveAccount() throws Exception {
		Mockito.when(customerRepository.deleteByCustomerId("1001001")).thenReturn(1);
		
		assertTrue(accountService.removeAccount("1001001") == 1);
	}
	
	/**
	 * Test case to ascertain the exception conditions.
	 * @throws Exception whenever any exception condition occurs
	 */
	@Test
	public void testRemoveAccount_throwsException() throws Exception {
		Mockito.when(customerRepository.deleteByCustomerId("1001001")).thenThrow(HashProofException.class);		
		
		HashProofException thrown = assertThrows(
				HashProofException.class,
		           () -> accountService.removeAccount("1001001"));

		    assertTrue(thrown.getHttpStatus().getReasonPhrase().contains("Bad Request"));
		    assertTrue(thrown.getHttpStatus().value() == 400);
	}

}
