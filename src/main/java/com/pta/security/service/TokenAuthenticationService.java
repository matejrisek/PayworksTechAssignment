package com.pta.security.service;

import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {

	private TokenAuthenticationService() {
		// hiding public constructor
	}
	
	static final long EXPIRATIONTIME = 600_000; // 10 Minutes
	static final String SECRET = "Secret";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_AUTHORIZATION = "Authorization";

	public static void addAuthentication(HttpServletResponse response, String username) {
		// @formatter:off
		String jwt = Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		// @formatter:on
		response.addHeader(HEADER_AUTHORIZATION, TOKEN_PREFIX + " " + jwt);
	}

	public static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_AUTHORIZATION);
		if (token != null) {
			// @formatter:off
			String user = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token.replaceAll(TOKEN_PREFIX, ""))
					.getBody()
					.getSubject();
			// @formatter:on
			
			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
			}
		}
		return null;
	}
}
