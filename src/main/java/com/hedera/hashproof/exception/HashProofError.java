package com.hedera.hashproof.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class HashProofError {

	private final HttpStatus status;
	private final String description;
}
