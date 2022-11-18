package com.hedera.hashproof.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * This entity class is responsible to keep customer's specific configuration details
 *
 */
@Data
@NoArgsConstructor(force = true)
@Entity
@Table(name = "HASHPROOF_CUSTOMER_CONFIG_DATA")
public class HashProofCustomerConfigData {
	public HashProofCustomerConfigData(String customerId, String writeData, String createdBy, Timestamp createTime) {
		this.customerId = customerId;
		this.writeData = writeData;
		this.createdBy = createdBy;
		this.createTime = createTime;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CONFIG_ID")
	private Long id;
	@Column(name = "CUSTOMER_ID", unique = true, nullable = false)
	private String customerId;
	@Column(name = "WRITE_DATA", length = 1)
	private String writeData;
	@Column(name = "CREATE_BY")
	private String createdBy;
	@Column(name = "UPDATE_BY")
	private String updatedBy;
	@Column(name = "CREATE_TIME")
	private Timestamp createTime;
	@Column(name = "UPDATE_TIME")
	private Timestamp updateTime;

}
