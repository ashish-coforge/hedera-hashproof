package com.hedera.hashproof.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hedera.hashproof.dto.HashProofAccountConfigResponse;
import com.hedera.hashproof.exception.HashProofException;
import com.hedera.hashproof.model.HashProofCustomerConfigData;
import com.hedera.hashproof.repository.HashProofCustomerConfigRepository;
import com.hedera.hashproof.service.util.HashProofHelper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HashProofAccountService {
	@Autowired
	HashProofCustomerConfigRepository customerRepository;

	/**
	 * 
	 * @param writeDate
	 * @return
	 * @throws HashProofException
	 */
	public HashProofAccountConfigResponse createAccount(String writeDate) throws HashProofException {
		log.info("createAccount:call");
		HashProofAccountConfigResponse hashProofAccountConfigResponse = null;
		//Randomly generated 6 digit customer id.customer id generation is temporary in HashProof
		//and will be replaced by Apigee generated customerId later on will be passed from Apigee API.
		String customerId = HashProofHelper.generateCustomerId();
		HashProofCustomerConfigData hashProofCustomerConfigData = new HashProofCustomerConfigData(customerId, writeDate,
				customerId, new Timestamp(System.currentTimeMillis()));
		try {
			customerRepository.save(hashProofCustomerConfigData);
			hashProofAccountConfigResponse = new HashProofAccountConfigResponse();
			hashProofAccountConfigResponse.setCustomerId(customerId);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			throw new HashProofException(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return hashProofAccountConfigResponse;
	}

	/**
	 * 
	 * @param customerId
	 * @param writeDate
	 * @throws HashProofException
	 */
	public void editAccount(String customerId, String writeDate) throws HashProofException {
		log.info("editAccount");
		HashProofCustomerConfigData hashProofCustomerConfigData;
		try {
			hashProofCustomerConfigData = customerRepository.findByCustomerId(customerId);
			hashProofCustomerConfigData.setUpdatedBy(customerId);
			hashProofCustomerConfigData.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			hashProofCustomerConfigData.setWriteData(writeDate);
			customerRepository.save(hashProofCustomerConfigData);
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			throw new HashProofException(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
