package br.com.sp.pratica.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Order(2)
@Scope("singleton")
public class RequestLoggingFilter extends OncePerRequestFilter{
	private Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);
	
	private static String oldRequest = "";
	
	//A variável acredita em primeiro momento que é a primeira requisição, após isso ela se torna false 
	private boolean isFirstRequest = true;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		StringBuffer requestURL = request.getRequestURL();
		
		if (oldRequest.isEmpty() && isFirstRequest) {
			oldRequest = requestURL.toString();
			isFirstRequest = false;
		}
		
		for (int i = 0; i < 10; i++) {
			logger.info("Antiga Requisição: {} --- Nova Requisição: {}", oldRequest, request.getRequestURL().toString());
		}
		
		oldRequest = requestURL.toString();
		
		filterChain.doFilter(request, response);
	}

}
