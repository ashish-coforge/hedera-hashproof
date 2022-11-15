package com.hedera.hashproof.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=false)
public class HashProofAccountConfigResponse extends Response{
	private String customerId;

}
