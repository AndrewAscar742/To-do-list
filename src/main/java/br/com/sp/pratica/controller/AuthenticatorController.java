package br.com.sp.pratica.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sp.pratica.domain.User;
import br.com.sp.pratica.dtos.AuthenticationDTO;
import br.com.sp.pratica.dtos.LoginResponseDTO;
import br.com.sp.pratica.services.security.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthenticatorController {
	
	//Essa clase é responsável pelo gerenciamento da authenticação do usuário
	private AuthenticationManager authenticationManager;
	
	private TokenService tokenService;

	public AuthenticatorController(AuthenticationManager authenticationManager, TokenService tokenService) {
		super();
		this.authenticationManager = authenticationManager;
		this.tokenService = tokenService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
		UsernamePasswordAuthenticationToken authenticationToken = 
				new UsernamePasswordAuthenticationToken(authenticationDTO.login(), authenticationDTO.password());
		
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		String token = tokenService.createToken((User) authentication.getPrincipal());
		
		
		return ResponseEntity.ok(new LoginResponseDTO(token));
	}
	
}
