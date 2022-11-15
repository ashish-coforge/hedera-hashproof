package com.hedera.hashproof.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "HASHPROOF_CUSTOMER_CONFIG_DATA")
public class HashProofCustomerConfigData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CONFIG_ID")
	private Long id;
	@Column(name = "CUSTOMER_ID")
	private String customerId;
	@Column(name = "WRITE_DATA")
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
