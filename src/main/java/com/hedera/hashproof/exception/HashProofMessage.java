package com.hedera.hashproof.exception;

public enum HashProofMessage {

	ACCOUNT_CREATION_FAIL("Account could not be created.Please try after some time."),
	ACCOUNT_CREATION_SUCCESS("Account created successfully.");

	String description;

	HashProofMessage(String description) {
		this.description = description;
	}

	public String gerDescription() {
		return description;
	}

}
