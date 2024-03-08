package br.com.sp.pratica.controller;

import java.net.URI;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/api/users")
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
	public ResponseEntity<Page<UserDTO>> listAllEnabled(@PageableDefault(size = 20) Pageable pageable){
		logger.debug("Listagem De Usuários Ativos");
		return ResponseEntity.ok(userService.listAllEnabled(pageable));
	}
	
	@GetMapping("/listDisabled")
	public ResponseEntity<Page<UserDTO>> listAllDisabled(@PageableDefault(size = 20) Pageable pageable){
		logger.debug("Listagem De Usuários Inativos");
		return ResponseEntity.ok(userService.listAllDisabled(pageable));
	}
	
	@GetMapping(ID)
	public ResponseEntity<UserDTO> findById(@PathVariable(ID_PARAM) Long id){
		logger.debug("Listagem Específica por ID de User");
		return ResponseEntity.ok(userService.findById(id));
	}
	
	@PostMapping("/save")
	public ResponseEntity<UserDTO> save(@RequestBody @Valid UserDTO userDTO, UriComponentsBuilder uriBuilder){
		logger.debug("Inicialização do save User");
		UserDTO user = userService.save(userDTO);
		
		URI uri = uriBuilder.path("/api/users" + ID).buildAndExpand(user.getId()).toUri();
		
		logger.debug("Finalização do save UserDTO");
		return ResponseEntity.created(uri).body(user);
	}
	
	@PutMapping("/update" + ID)
	public ResponseEntity<UserDTO> update(@PathVariable(ID_PARAM) Long id ,@RequestBody @Valid UserDTO userDTO) {
		logger.debug("Inicialização do update User");
		
		userDTO.setId(id);
		UserDTO user = userService.update(userDTO);
		
		logger.debug("Finalização do update User");
		return ResponseEntity.ok(user);
	}
	
	@DeleteMapping("/delete" + ID)
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
