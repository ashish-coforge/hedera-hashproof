package com.hedera.hashproof.exception;

public enum HashProofMessage {

	TRANSACTION_FAIL("Transaction Fail."), MANDATORY_INPUTS("Please provide mandatory inputs"),
	INVALID_INPUTS("Please provide valid input(s)"), ACCOUNT_CREATION_SUCCESS("Account created successfully."),
	ACCOUNT_UPDATE_SUCCESS("Account updated successfully."), ACCOUNT_REMOVE_SUCCESS("Account removed successfully."),
	ACCOUNT_NOT_FOUND("Account doesn't exist.");

	String description;

	HashProofMessage(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
