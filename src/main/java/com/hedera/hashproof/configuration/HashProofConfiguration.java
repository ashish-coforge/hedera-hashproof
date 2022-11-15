package com.hedera.hashproof.configuration;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hedera.hashgraph.sdk.Client;
import com.hedera.hashproof.constant.HashProofNetworkEnum;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class HashProofConfiguration {
	@Autowired
	private HashProofEnvConfiguration config;

	@Bean
	public Client getHederaClient() {
		HashProofNetworkEnum network = config.getEnvironment();
		Client hederaClient = null;

		if (network == null) {
			log.error("HEDERA_NETWORK environment variable not set - exiting");
			System.exit(1);
		}

		switch (network) {
		case TESTNET:
			hederaClient = Client.forTestnet();
			try {
				hederaClient.setMirrorNetwork(Collections.singletonList(config.getMirrorNodeAddress()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
		case PREVIEWNET:
			hederaClient = Client.forPreviewnet();
			break;
		case MAINNET:
			hederaClient = Client.forMainnet();
			break;
		}
		hederaClient.setOperator(config.getOperatorId(), config.getOperatorKey());
		return hederaClient;

	}

}
