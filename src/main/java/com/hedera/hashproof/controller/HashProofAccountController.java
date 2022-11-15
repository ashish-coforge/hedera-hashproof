package com.hedera.hashproof.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hedera.hashproof.dto.HashProofAccountConfigRequest;
import com.hedera.hashproof.dto.Response;
import com.hedera.hashproof.exception.HashProofMessage;
import com.hedera.hashproof.service.HashProofAccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Api(tags = "HashProof")
@RequestMapping(path = "/hashproof")
public class HashProofAccountController {

	@Autowired
	HashProofAccountService hashProofAccountService;

	@PostMapping("/registration")
	@ApiOperation("Register HashProof User")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Customer Id"),
			@ApiResponse(code = 400, message = "Something went wrong") })
	public Response createAccount(@ApiParam("Create Customer Account") HashProofAccountConfigRequest request)
			throws Exception {
		log.info("createAccount");
		Response response = hashProofAccountService.createAccount();
		response.setMessage(HashProofMessage.ACCOUNT_CREATION_SUCCESS.gerDescription());
		response.setStatus(HttpStatus.OK.value());
		return response;
	}

}
