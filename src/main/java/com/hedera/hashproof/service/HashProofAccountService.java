package com.hedera.hashproof.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hedera.hashproof.dto.HashProofAccountConfigResponse;
import com.hedera.hashproof.repository.HashProofCustomerConfigRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HashProofAccountService {
	@Autowired
	HashProofCustomerConfigRepository customerRepository;

	public HashProofAccountConfigResponse createAccount() {
		HashProofAccountConfigResponse hashProofAccountConfigResponse = null;
		log.info("register");
		return hashProofAccountConfigResponse;
	}

}
