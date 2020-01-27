package com.bridgelabz.fundoonotes.utility;

import java.io.UnsupportedEncodingException;

import org.springframework.context.annotation.Configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Configuration
public class JwtProvider {
	private final long EXPIRATION_TIME = 9000;
	private final String secret = "Nancy";
	//parsing token
	public  String generateToken(Long id) {
		return JWT.create().withClaim("id", id).sign(Algorithm.HMAC512(secret));
	}

	
//converting token
		public Long decodeToken(String token) {
		
		
		return  JWT.require(Algorithm.HMAC512(secret)).build().verify(token).getClaim("id").asLong();
		

	
		}

		public String parseToken(String token) {
						return JWT.require(Algorithm.HMAC512(secret)).build().verify(token).getClaim("email").asString();
		}
	public Long parseJWT(String token)
//			throws JWTVerificationException, IllegalArgumentException, UnsupportedEncodingException {
throws JWTVerificationException,IllegalArgumentException,UnsupportedEncodingException{
		Long userid = 0L;
		if (token != null) {
			userid = JWT.require(Algorithm.HMAC512(secret)).build().verify(token).getClaim("id").asLong();
		}
		return userid;
	}
}