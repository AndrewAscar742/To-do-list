package br.com.sp.pratica.controller;

import java.net.URI;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sp.pratica.dtos.UserDTO;
import br.com.sp.pratica.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/users", produces = "application/json")
@Tag(name = "UserController")
public class UserController {
	
	@Qualifier("UserServiceImpl")
	private final UserService userService;
	
	private final String ID = "/{User_id}";
	private final String ID_PARAM = "User_id";
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("/listEnabled")
	@Operation(method = "GET", deprecated = false, summary = "Listagem de Usuários Ativos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Listagem de Usuários Ativos com Sucesso")
	})
	public ResponseEntity<Page<UserDTO>> listAllEnabled(@PageableDefault(size = 20) Pageable pageable){
		logger.debug("Listagem De Usuários Ativos");
		return ResponseEntity.ok(userService.listAllEnabled(pageable));
	}
	
	@GetMapping("/listDisabled")
	@Operation(method = "GET", deprecated = false, summary = "Listagem de Usuários Inátivos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Listagem de Usuários Inátivos com Sucesso")
	})
	public ResponseEntity<Page<UserDTO>> listAllDisabled(@PageableDefault(size = 20) Pageable pageable){
		logger.debug("Listagem De Usuários Inativos");
		return ResponseEntity.ok(userService.listAllDisabled(pageable));
	}
	
	@GetMapping(ID)
	@Operation(method = "GET", deprecated = false, summary = "Listagem de Usuário por ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Busca de Usuário por ID com sucesso"),
			@ApiResponse(responseCode = "404", description = "Usuário não encontrado")
	})
	public ResponseEntity<UserDTO> findById(@Parameter(name = "User_ID" ,description = "ID do usuário") @PathVariable(ID_PARAM) Long id){
		logger.debug("Listagem Específica por ID de User");
		return ResponseEntity.ok(userService.findById(id));
	}
	
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(method = "POST", deprecated = false, summary = "Cadastro de Usuário")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Usuário Cadastrado com Sucesso"),
			@ApiResponse(responseCode = "400", description = "Dados Incorretos, tente novamente")
	})
	public ResponseEntity<UserDTO> save(@RequestBody @Valid UserDTO userDTO, UriComponentsBuilder uriBuilder){
		logger.debug("Inicialização do save User");
		UserDTO user = userService.save(userDTO);
		
		URI uri = uriBuilder.path("/api/users" + ID).buildAndExpand(user.getId()).toUri();
		
		logger.debug("Finalização do save UserDTO");
		return ResponseEntity.created(uri).body(user);
	}
	
	@PutMapping(value = "/update" + ID, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Operation(method = "PUT", deprecated = false, summary = "Atualização de Usuário")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuário Atualizado com Sucesso"),
			@ApiResponse(responseCode = "400", description = "Dados Incorretos, tente novamente"),
			@ApiResponse(responseCode = "404", description = "Usuário não encontrado")
	})
	public ResponseEntity<UserDTO> update(@PathVariable(ID_PARAM) Long id ,@RequestBody @Valid UserDTO userDTO) {
		logger.debug("Inicialização do update User");
		
		userDTO.setId(id);
		UserDTO user = userService.update(userDTO);
		
		logger.debug("Finalização do update User");
		return ResponseEntity.ok(user);
	}
	
	@DeleteMapping("/delete" + ID)
	@Operation(method = "DELETE", deprecated = false, summary = "Inativação de um Usuário")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Deletar Usuário por ID com Sucesso"),
			@ApiResponse(responseCode = "404", description = "Usuário não encontrado")
	})
	public ResponseEntity<?> delete(@PathVariable(ID_PARAM) Long id){
		logger.debug("Inicialização do delete User");
		boolean delete = userService.delete(id);
		
		logger.debug("Finalização do delete User");
		if (delete)
			return ResponseEntity.noContent().build();
		else 
			return ResponseEntity.notFound().build();
	}
	
}
