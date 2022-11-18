package com.hedera.hashproof.dto;

import com.hedera.hashproof.exception.HashProofError;

import lombok.Data;

@Data
public class Response {
	private String message;
	private int code;
	private HashProofError error;
}
