package com.hedera.hashproof.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hedera.hashproof.dto.Response;
import com.hedera.hashproof.exception.HashProofError;
import com.hedera.hashproof.exception.HashProofException;
import com.hedera.hashproof.exception.HashProofMessage;
import com.hedera.hashproof.service.HashProofAccountService;
import com.hedera.hashproof.util.HashProofHelper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * This controller class is responsible to perform Customer's configuration
 * related changes. Customer can opt to write it's data on HCS in its original
 * format.
 *
 */
@Slf4j
@RestController
@Api(tags = "HashProof")
@RequestMapping(path = "/hashproof")
public class HashProofAccountController {

	@Autowired
	HashProofAccountService hashProofAccountService;

	/**
	 * Default value of writeData = "Y"
	 * 
	 * @param writeData
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/registration")
	@ApiOperation("Register HashProof User")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Account created successfully."),
			@ApiResponse(code = 400, message = "Transaction fail.") })
	public Response createAccount(@RequestParam(required = false) String writeData) throws Exception {
		Response response = new Response();
		log.info("createAccount:writeData" + writeData);
		try {
			if (writeData == null || writeData.isBlank()) {
				writeData = "Y";
			} else {
				writeData = HashProofHelper.validateConfiDataInput(writeData);
			}
			response = hashProofAccountService.createAccount(writeData);
			response.setMessage(HashProofMessage.ACCOUNT_CREATION_SUCCESS.getDescription());
			response.setCode(HttpStatus.OK.value());
		} catch (HashProofException e) {
			response = HashProofHelper.generateErrorResponse(e.getHttpStatus(), e.getMessage());
		}

		return response;
	}

	/**
	 * 
	 * @param customerId
	 * @param writeData
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/account/edit")
	@ApiOperation("Edit HashProof User Account")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Account updated successfully."),
			@ApiResponse(code = 400, message = "Transaction fail.") })
	public Response editAccount(@RequestHeader("X-CustomerId") String customerId,
			@RequestParam("writeData") String writeData) throws Exception {
		Response response = new Response();
		try {
			if (writeData == null || writeData.isBlank()) {
				response.setError((new HashProofError(HttpStatus.BAD_REQUEST,
						HashProofMessage.MANDATORY_INPUTS.getDescription())));
				response.setMessage(HashProofMessage.MANDATORY_INPUTS.getDescription());
				response.setCode(HttpStatus.BAD_REQUEST.value());
				return response;
			} else {
				writeData = HashProofHelper.validateConfiDataInput(writeData);
			}
			hashProofAccountService.editAccount(customerId, writeData);
			response.setMessage(HashProofMessage.ACCOUNT_UPDATE_SUCCESS.getDescription());
			response.setCode(HttpStatus.OK.value());
		} catch (HashProofException e) {
			response = HashProofHelper.generateErrorResponse(e.getHttpStatus(), e.getMessage());
		}

		return response;
	}

	/**
	 * 
	 * @param customerId
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/account/remove")
	@ApiOperation("Remove HashProof User Account")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Account removed successfully."),
			@ApiResponse(code = 400, message = "Transaction fail.") })
	public Response removeAccount(@RequestParam("customerId") String customerId) throws Exception {
		Response response = new Response();
		int result;
		log.info("removeAccount,cstomerId==" + customerId);
		try {
			result = hashProofAccountService.removeAccount(customerId);
			if (result == 1) {
				response.setMessage(HashProofMessage.ACCOUNT_REMOVE_SUCCESS.getDescription());
				response.setCode(HttpStatus.OK.value());
			} else {
				response.setMessage(HashProofMessage.ACCOUNT_NOT_FOUND.getDescription());
				response.setCode(HttpStatus.NOT_FOUND.value());
			}

		} catch (HashProofException e) {
			response = HashProofHelper.generateErrorResponse(e.getHttpStatus(), e.getMessage());
		}
		return response;
	}
}
