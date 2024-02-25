package br.com.sp.pratica.services.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.sp.pratica.repositories.UserRepository;

/*
 * Essa classe de serviço é chamada automaticamente pelo Spring Security para realizar autenticação
 */

@Service
public class AuthorizationService implements UserDetailsService{
	
	private final UserRepository userRepository;
	
	public AuthorizationService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByEmail(username);
	}

}
