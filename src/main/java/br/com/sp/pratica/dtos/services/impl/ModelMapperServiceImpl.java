package br.com.sp.pratica.dtos.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.sp.pratica.domain.ToDoList;
import br.com.sp.pratica.domain.User;
import br.com.sp.pratica.dtos.ToDoListDTO;
import br.com.sp.pratica.dtos.UserDTO;
import br.com.sp.pratica.dtos.postDtos.PostToDoList;
import br.com.sp.pratica.dtos.putDtos.PutToDoList;
import br.com.sp.pratica.dtos.services.ModelMapperService;

@Service
public class ModelMapperServiceImpl implements ModelMapperService {
	
	private final ModelMapper modelMapper;

	public ModelMapperServiceImpl(ModelMapper modelMapper) {
		super();
		this.modelMapper = modelMapper;
	}

	@Override
	public UserDTO convertUserToUserDTO(User user) {
		return modelMapper.map(user, UserDTO.class);
	}
	
	@Override
	public User converUserDTO_to_User(UserDTO userDTO) {
		return modelMapper.map(userDTO, User.class);
	}
	
	@Override
	public ToDoListDTO convertToDoListToDTO(ToDoList to_do_list) {
		return modelMapper.map(to_do_list, ToDoListDTO.class);
	}
	
	@Override
	public ToDoList convertPostToDoListToToDoList(PostToDoList toDoList) {
		return modelMapper.map(toDoList, ToDoList.class);
		
	}
	
	@Override
	public ToDoList convertPutToDoListToTodoList(PutToDoList putToDoList) {
		return modelMapper.map(putToDoList, ToDoList.class);
	}
}
