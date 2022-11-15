package com.hedera.hashproof.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hedera.hashproof.model.HashProofCustomerConfigData;

public interface HashProofCustomerConfigRepository extends JpaRepository<HashProofCustomerConfigData, Integer> {
}
