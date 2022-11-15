package com.hedera.hashproof.configuration;

import java.util.Objects;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.hedera.hashgraph.sdk.AccountId;
import com.hedera.hashgraph.sdk.PrivateKey;
import com.hedera.hashproof.constant.HashProofConstants;
import com.hedera.hashproof.constant.HashProofNetworkEnum;

@Configuration
@ConfigurationProperties(prefix = HashProofConstants.PREFIX)
public class HashProofEnvConfiguration {

	private String operatorId;
	private String operatorKey;
	private String mirrorNodeAddress;
	private String mirrorNodeURI;
	private String mirrorNodeApiVersion;
	private String environment;
	private String hederaTopicId;

	public AccountId getOperatorId() {
		return AccountId.fromString(Objects.requireNonNull(this.operatorId));
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public PrivateKey getOperatorKey() {
		return PrivateKey.fromString(Objects.requireNonNull(this.operatorKey));
	}

	public void setOperatorKey(String operatorKey) {
		this.operatorKey = operatorKey;
	}

	public String getMirrorNodeAddress() {
		return mirrorNodeAddress;
	}

	public void setMirrorNodeAddress(String mirrorNodeAddress) {
		this.mirrorNodeAddress = mirrorNodeAddress;
	}

	public String getMirrorNodeURI() {
		return mirrorNodeURI;
	}

	public void setMirrorNodeURI(String mirrorNodeURI) {
		this.mirrorNodeURI = mirrorNodeURI;
	}

	public String getMirrorNodeApiVersion() {
		return mirrorNodeApiVersion;
	}

	public void setMirrorNodeApiVersion(String mirrorNodeApiVersion) {
		this.mirrorNodeApiVersion = mirrorNodeApiVersion;
	}

	public HashProofNetworkEnum getEnvironment() {
		switch (environment) {
		case "TESTNET":
			return HashProofNetworkEnum.TESTNET;
		case "PREVIEWNET":
			return HashProofNetworkEnum.PREVIEWNET;
		case "MAINNET":
			return HashProofNetworkEnum.MAINNET;
		default:
			return null;

		}
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getHederaTopicId() {
		return hederaTopicId;
	}

	public void setHederaTopicId(String hederaTopicId) {
		this.hederaTopicId = hederaTopicId;
	}

}
