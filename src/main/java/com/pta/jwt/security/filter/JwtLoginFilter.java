package com.pta.jwt.security.filter;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pta.jwt.security.entity.AccountCredentials;
import com.pta.security.service.TokenAuthenticationService;

public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

	public JwtLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		AccountCredentials credentials = new ObjectMapper().readValue(request.getInputStream(), AccountCredentials.class);
		// @formatter:off
 		return getAuthenticationManager().authenticate(
 			new UsernamePasswordAuthenticationToken(
				credentials.getUsername(), 
				credentials.getPassword(), 
				Collections.emptyList()
			)
 		);
		// @formatter:on
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
			throws IOException, ServletException {
		TokenAuthenticationService.addAuthentication(response, authResult.getName());
	}

}
