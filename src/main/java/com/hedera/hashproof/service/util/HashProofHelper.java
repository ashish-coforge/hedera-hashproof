package com.hedera.hashproof.service.util;

import java.util.Random;

import org.springframework.http.HttpStatus;

import com.hedera.hashproof.dto.Response;
import com.hedera.hashproof.exception.HashProofError;
import com.hedera.hashproof.exception.HashProofException;
import com.hedera.hashproof.exception.HashProofMessage;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * A helper class for utility methods
 *
 */
@Slf4j
public class HashProofHelper {
	/**
	 * validate writeData and accepts only "Y","Yes","N","No"
	 * 
	 * @param writeData
	 * @return
	 * @throws HashProofException
	 */
	public static String validateConfiDataInput(String writeData) throws HashProofException {
		log.info("validateConfiDataInput:call");
		if (writeData.equalsIgnoreCase("Y") || writeData.equalsIgnoreCase("Yes")) {
			writeData = "Y";
		} else if (writeData.equalsIgnoreCase("N") || writeData.equalsIgnoreCase("No")) {
			writeData = "N";
		} else {
			throw new HashProofException(HashProofMessage.INVALID_INPUTS.getDescription(), HttpStatus.BAD_REQUEST);
		}
		return writeData;
	}

	/**
	 * method to generate error response
	 * 
	 * @param httpStatus
	 * @param message
	 * @return
	 */
	public static Response generateErrorResponse(HttpStatus httpStatus, String message) {
		log.info("generateErrorResponse:call");
		Response response = new Response();
		response.setError((new HashProofError(httpStatus, message)));
		response.setMessage(HashProofMessage.TRANSACTION_FAIL.getDescription());
		response.setCode(httpStatus.value());
		return response;
	}

	/**
	 * method returns a random customer id of length 6.
	 * Generation of customer id is temporary in HashProof and will be replaced by Apigee
	 * generated customerId later on.
	 * 
	 * @return
	 */
	public static String generateCustomerId() {
		Random randomNum = new Random();
		int customerNumber = randomNum.nextInt(999999);
		String customerId = String.format("%06d", customerNumber);
		return customerId;
	}

}
