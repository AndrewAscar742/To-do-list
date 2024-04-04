package br.com.sp.pratica.config.profiles;

import java.util.Arrays;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.sp.pratica.domain.ToDoList;
import br.com.sp.pratica.domain.User;
import br.com.sp.pratica.enums.Genre;
import br.com.sp.pratica.enums.TaskStatus;
import br.com.sp.pratica.enums.UserRole;
import br.com.sp.pratica.repositories.ToDoListRepository;
import br.com.sp.pratica.repositories.UserRepository;

@Configuration
@Scope("singleton")
@Profile("dev")
public class DevProfile {
	private final Logger logger = LoggerFactory.getLogger(DevProfile.class);
	
	private final UserRepository userRepository;
	private final ToDoListRepository toDoListRepository;

	public DevProfile(UserRepository userRepository, ToDoListRepository toDoListRepository) {
		super();
		this.userRepository = userRepository;
		this.toDoListRepository = toDoListRepository;
	}
	
	@Bean
	public void insertDB() {
		logger.debug("Iniciando Insert no banco de dados H2");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		User user1 = new User("Andrew Matos", 20, Genre.MASCULINO, new Date(), new Date(), "andrewteste@fake.com", encoder.encode("123"), true, UserRole.ADMIN);
		User user2 = new User("José Felipe", 35, Genre.MASCULINO, new Date(), new Date(), "josefelipe@fake.com", encoder.encode("1234"), true, UserRole.ADMIN);
		User user3 = new User("Beatriz", 19, Genre.FEMININO, new Date(), new Date(), "beatriz@fake.com", encoder.encode("12345"), true, UserRole.USER);
		User user4 = new User("Teste falso", 24, Genre.MASCULINO, new Date(), new Date(), "testefalso@fake.com", encoder.encode("123456"), false, UserRole.USER);
		
		userRepository.saveAll(Arrays.asList(user1, user2, user3, user4));
		
		ToDoList toDoList1 = new ToDoList("Listagem Geral Ativos", "Ver a listagem de usuários ativos", new Date(), new Date(), TaskStatus.EM_ANDAMENTO, user1);
		ToDoList toDoList2 = new ToDoList("Listagem Geral Inativos", "Ver a listagem de usuários inátivos", new Date(), new Date(), TaskStatus.EM_ANDAMENTO, user1);
		ToDoList toDoList3 = new ToDoList("Deletar Usuário", "Obter sucesso ao inativar um usuário átivo", new Date(), new Date(), TaskStatus.EM_ANDAMENTO, user2);
		
		toDoListRepository.saveAll(Arrays.asList(toDoList1, toDoList2, toDoList3));
		logger.debug("Finalizando Insert no banco de dados H2");
	}
	
}
