package br.com.sp.pratica.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sp.pratica.dtos.ToDoListDTO;
import br.com.sp.pratica.dtos.postDtos.PostToDoList;
import br.com.sp.pratica.dtos.putDtos.PutToDoList;
import br.com.sp.pratica.dtos.putDtos.StatusToDoListDTO;
import br.com.sp.pratica.services.ToDoListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/to-do-list", produces = "application/json")
@Tag(name = "ToDoListController")
public class ToDoListController {
	
	private final String ID = "/{ToDoList_id}";
	private final String ID_PARAM = "ToDoList_id";
	
	private Logger logger = LoggerFactory.getLogger(ToDoListController.class);
	
	private ToDoListService toDoListService;

	public ToDoListController(ToDoListService toDoListService) {
		super();
		this.toDoListService = toDoListService;
	}
	
	@GetMapping
	@Operation(method = "GET", deprecated = false, summary = "Listagem Geral de ToDolist")
	public ResponseEntity<List<ToDoListDTO>> listAll() {
		logger.debug("Listagem Geral de ToDoList");
		return ResponseEntity.ok(toDoListService.listAll());		
		
	}
	
	@GetMapping(ID)
	@Operation(method = "GET", deprecated = false, summary = "Buscar ToDoList por ID")
	public ResponseEntity<ToDoListDTO> findById(@PathVariable(ID_PARAM) Long id) {
		logger.debug("Listagem Específica por ID de ToDoList");
		return ResponseEntity.ok(toDoListService.findById(id));
	}
	
	@PostMapping("/save")
	@Operation(method = "POST", deprecated = false, summary = "Cadastramento de um ToDoList")
	public ResponseEntity<ToDoListDTO> save(@RequestBody @Valid PostToDoList toDoList, UriComponentsBuilder uriBuilder) {
		logger.debug("Inicialização do save ToDoList");
		ToDoListDTO toDoListDto = toDoListService.save(toDoList);
		
		URI uri = uriBuilder.path("/api/to-do-list/{toDoList_id}").buildAndExpand(toDoListDto.getId()).toUri();
		
		logger.debug("Finalização do save ToDoList");
		return ResponseEntity.created(uri).body(toDoListDto);
	}
	
	@PutMapping("/update" + ID)
	@Operation(method = "PUT", deprecated = false, summary = "Atualização de um ToDoList")
	public ResponseEntity<ToDoListDTO> update(@PathVariable(ID_PARAM) Long id, @RequestBody @Valid PutToDoList putToDoList) {
		logger.debug("Inicialização do update ToDoList");
		ToDoListDTO toDoListDto = toDoListService.update(id, putToDoList);
		
		logger.debug("Finalização do update ToDoList");
		return ResponseEntity.ok(toDoListDto);
	}
	
	@PatchMapping("/update" + ID + "/status")
	@Operation(method = "PATCH", deprecated = false, summary = "Atualização de Status sobre o ToDoList")
	public ResponseEntity<ToDoListDTO> updateStatus(@PathVariable(ID_PARAM) Long id, @RequestBody @Valid StatusToDoListDTO status){
		logger.debug("Inicialização do updateStatus ToDoList");
		ToDoListDTO toDoListDto = toDoListService.updateStatus(id, status);
		
		logger.debug("Finalização do updateStatus ToDoList");
		return ResponseEntity.ok(toDoListDto);
	}
	
	@DeleteMapping("/delete" + ID)
	@Operation(method = "DELETE", deprecated = false, summary = "Deletar Permanentemente um ToDoList")
	public ResponseEntity<Object> delete(@PathVariable(ID_PARAM) Long id) {
		logger.debug("Inicialização do delete ToDoList");
		boolean deleted = toDoListService.delete(id);
		
		logger.debug("Finalização do delete ToDoList");
		if (deleted)
			return ResponseEntity.noContent().build();
		else
			return ResponseEntity.notFound().build();
	}
	
	
}
