package com.hedera.hashproof.exception;

public class HashProofError {

	int code;
	String description;

	public HashProofError(int code, String description) {
		this.code = code;
		this.description = description;
	}

}
