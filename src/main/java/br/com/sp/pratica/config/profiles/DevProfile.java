package br.com.sp.pratica.config.profiles;

import java.util.Arrays;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

import br.com.sp.pratica.domain.User;
import br.com.sp.pratica.enums.Genre;
import br.com.sp.pratica.enums.UserRole;
import br.com.sp.pratica.repositories.UserRepository;

@Configuration
@Scope("singleton")
@Profile("dev")
public class DevProfile {
	private final Logger logger = LoggerFactory.getLogger(DevProfile.class);
	
	private final UserRepository userRepository;

	public DevProfile(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	@Bean
	public void insertDB() {
		logger.debug("Iniciando Insert no banco de dados H2");
		User user1 = new User("Andrew Matos", 20, Genre.MASCULINO, new Date(), new Date(), "andrewteste@fake.com", "123", true, UserRole.ADMIN);
		User user2 = new User("José Felipe", 35, Genre.MASCULINO, new Date(), new Date(), "josefelipe@fake.com", "123", true, UserRole.ADMIN);
		
		userRepository.saveAll(Arrays.asList(user1, user2));
		logger.debug("Finalizando Insert no banco de dados H2");
	}
	
}
