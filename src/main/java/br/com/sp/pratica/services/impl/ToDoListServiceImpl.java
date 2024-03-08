package br.com.sp.pratica.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.sp.pratica.domain.ToDoList;
import br.com.sp.pratica.domain.User;
import br.com.sp.pratica.dtos.ToDoListDTO;
import br.com.sp.pratica.dtos.postDtos.PostToDoList;
import br.com.sp.pratica.dtos.putDtos.PutToDoList;
import br.com.sp.pratica.dtos.putDtos.StatusToDoListDTO;
import br.com.sp.pratica.dtos.services.ModelMapperService;
import br.com.sp.pratica.repositories.ToDoListRepository;
import br.com.sp.pratica.services.ToDoListService;
import br.com.sp.pratica.services.UserService;
import br.com.sp.pratica.services.exceptions.ToDoListNotFoundException;

@Service
public class ToDoListServiceImpl implements ToDoListService{
	
	@Value("${TO_DO_LIST_NOT_FOUND_EXCEPTION}")
	private String TO_DO_LIST_NOT_FOUND_EXCEPTION = new String();

	private ToDoListRepository toDoListRepository;
	private UserService userService;
	private ModelMapperService modelMapperService;
	
	public ToDoListServiceImpl(ToDoListRepository toDoListRepository, UserService userService, 
			ModelMapperService modelMapperService) {
		super();
		this.toDoListRepository = toDoListRepository;
		this.userService = userService;
		this.modelMapperService = modelMapperService;
	}


	@Override
	public List<ToDoListDTO> listAll() {
		List<ToDoListDTO> list = toDoListRepository.findAll()
				.stream().map(to_do_list -> modelMapperService.convertToDoListToDTO(to_do_list)).toList();
		
		return list;
		
	}
	
	@Override
	public ToDoListDTO findById(Long id) {
		ToDoList toDoList = toDoListRepository
				.findById(id)
				.orElseThrow(() -> new ToDoListNotFoundException(TO_DO_LIST_NOT_FOUND_EXCEPTION));
		
		ToDoListDTO toDoListDTO = modelMapperService.convertToDoListToDTO(toDoList);
		return toDoListDTO;
	}
	
	@Override
	public ToDoListDTO save(PostToDoList toDoList) {
		User user = userService.findUser(toDoList.getUser_id());
		ToDoList entity = modelMapperService.convertPostToDoListToToDoList(toDoList);
		
		entity.setUser(user);
		entity.setDate_initial(new Date());
		toDoListRepository.save(entity);
		
		ToDoListDTO toDoListDTO = modelMapperService.convertToDoListToDTO(entity);
		toDoListDTO.setUserDTO(modelMapperService.convertUserToUserDTO(entity.getUser()));
		return toDoListDTO;
	}
	
	@Override
	public ToDoListDTO update(Long id, PutToDoList putToDoList) {
		ToDoList toDoList = toDoListRepository
				.findById(id)
				.orElseThrow(() -> new ToDoListNotFoundException(TO_DO_LIST_NOT_FOUND_EXCEPTION));
		
		ToDoList updateDomain = modelMapperService.convertPutToDoListToTodoList(putToDoList);
		
		this.merge(toDoList, updateDomain);
		toDoListRepository.save(toDoList);
		
		ToDoListDTO toDoListDTO = modelMapperService.convertToDoListToDTO(toDoList);
		return toDoListDTO;
	}
	
	@Override
	public ToDoListDTO updateStatus(Long id, StatusToDoListDTO status) {
		ToDoList toDoList = toDoListRepository
				.findById(id)
				.orElseThrow(() -> new ToDoListNotFoundException(TO_DO_LIST_NOT_FOUND_EXCEPTION));
		
		toDoList.setStatus(status.status());
		toDoList = toDoListRepository.saveAndFlush(toDoList);
		
		return modelMapperService.convertToDoListToDTO(toDoList);
	}
	
	private void merge(ToDoList existentDomain, ToDoList updateDomain) {
		existentDomain.setTitle(updateDomain.getTitle());
		existentDomain.setDate_initial(updateDomain.getDate_initial());
		existentDomain.setDue_date(updateDomain.getDue_date());
		existentDomain.setDescription(updateDomain.getDescription());
		
	}


	@Override
	public boolean delete(Long id) {
		Optional<ToDoList> todoList = toDoListRepository.findById(id);
		
		if (todoList.isPresent()) {
			toDoListRepository.delete(todoList.get());
			return true;
		} else {
			return false;
		}
		
		
	}
}
