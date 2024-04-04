package br.com.sp.pratica.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.sp.pratica.repositories.UserRepository;
import br.com.sp.pratica.services.security.TokenService;

@Component
@Order(1)
public class SecurityAuthenticatorFilter extends OncePerRequestFilter {
	private Logger logger = LoggerFactory.getLogger(SecurityAuthenticatorFilter.class);
	
	private TokenService tokenService;
	
	private UserRepository userRepository;
	
	public SecurityAuthenticatorFilter(TokenService tokenService, UserRepository userRepository) {
		super();
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = getToken(request);
		
		if (token != null) {
			String emailSubject = tokenService.validateToken(token);
			UserDetails userDetails = userRepository.findByEmail(emailSubject);
			
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response);
		
	}

	private String getToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		
		if (token != null) {
			token = token.replace("Bearer ", "");
			return token;
		}
		
		return null;
	}

}
