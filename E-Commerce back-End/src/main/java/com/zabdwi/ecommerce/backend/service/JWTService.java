package com.zabdwi.ecommerce.backend.service;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.zabdwi.ecommerce.backend.model.LocalUser;

@Service
public class JWTService {
	@Value("${jwt.algorithm.key}")
	private String algorithmKey;

	@Value("${jwt.issuer}")
	private String issuer;

	@Value("${jwt.expiryInSeconds}")
	private int expiryInSeconds;

	private Algorithm algorithm;

	private static final String USERNAME_KEY = "USERNAME";

	@PostConstruct
	public void postConstruct() throws Exception {
		algorithm = Algorithm.HMAC256(algorithmKey);
	}

	public String generateJWT(LocalUser user) {
		return JWT.create()
				.withClaim(USERNAME_KEY, user.getUserName())
				.withExpiresAt(new Date(System.currentTimeMillis() + (1000 * expiryInSeconds)))
				.withIssuer(issuer)
				.sign(algorithm);

	}

}
