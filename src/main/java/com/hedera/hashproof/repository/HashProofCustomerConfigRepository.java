package com.hedera.hashproof.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hedera.hashproof.model.HashProofCustomerConfigData;

public interface HashProofCustomerConfigRepository extends JpaRepository<HashProofCustomerConfigData, Integer> {
	/**
	 * 
	 * @param customerId
	 * @return
	 */
	public HashProofCustomerConfigData findByCustomerId(String customerId);

	/**
	 * 
	 * @param customerId
	 * @return
	 */
	@Transactional
	public int deleteByCustomerId(String customerId);
}
