package br.com.sp.pratica.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.sp.pratica.filter.SecurityAuthenticatorFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	private final String USER_ENDPOINT = "/api/users/";
	
	@Autowired
	SecurityAuthenticatorFilter securityAuthenticatorFilter;
	
	public SecurityConfiguration() {
		// TODO Auto-generated constructor stub
	}
	
	@Bean
	public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(authorize -> authorize.antMatchers(HttpMethod.GET, USER_ENDPOINT + "listEnabled").hasRole("ADMIN"))
				.authorizeHttpRequests(authorize -> authorize.antMatchers(HttpMethod.GET, USER_ENDPOINT + "listDisabled").hasRole("ADMIN"))
				.authorizeHttpRequests(authorize -> authorize.antMatchers(HttpMethod.DELETE, USER_ENDPOINT).hasRole("ADMIN"))
				.authorizeHttpRequests(authorize -> authorize.antMatchers("/auth/login").permitAll())
				.authorizeHttpRequests().anyRequest().authenticated().and()
				.addFilterBefore(securityAuthenticatorFilter, UsernamePasswordAuthenticationFilter.class)
				.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.build();
		}
	
	/*
	 * Este método é responsável pela instanciação do AuthenticationManager
	 */
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	/*
	 * Este método é reponsável pela instanciação do objeto e criptografia da senha dos usuários
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
