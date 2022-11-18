package com.hedera.hashproof.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class HashProofException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final String message;
	private final HttpStatus httpStatus;

}
