package br.com.sp.pratica.services.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import br.com.sp.pratica.domain.User;

@Service
public class TokenService {
	
	// É necessário o usuário como parâmetro para validar o nível de permissão do
	// usuário de acordo com sua role
	public String createToken(User user) {
		/*
		 * O algoritmo é utilizado para criação e validação de hash
		 * O que está sendo passado como parâmetro é o secret, é utilizado como uma senha para tornar o token JWT única
		 * withIssuer : identificação da API
		 * withSubject: identificação do usuário responsável pelo token
		 * withExpiresAt: data de expiração
		 * sign: criação do token
		 */
		
		try {
			Algorithm algorithm = Algorithm.HMAC256("teste");
			return JWT.create()
					.withIssuer("to-do-list-api")
					.withSubject(user.getEmail())
					.withExpiresAt(expirationDate())
					.sign(algorithm);
		} catch (JWTCreationException e) {
			throw new RuntimeException("Error while generating JWT Token", e);
		}

	}
	
	private Instant expirationDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256("teste");
			return JWT.require(algorithm)
					.withIssuer("to-do-list-api")
					.build()
					.verify(token)
					.getSubject();
		} catch (Exception e) {
			throw new RuntimeException("Error while validating JWT Token", e);
		}
	}
	
	
}
