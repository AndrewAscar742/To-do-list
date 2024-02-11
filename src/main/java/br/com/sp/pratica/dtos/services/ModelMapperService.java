package br.com.sp.pratica.dtos.services;

import br.com.sp.pratica.domain.ToDoList;
import br.com.sp.pratica.domain.User;
import br.com.sp.pratica.dtos.ToDoListDTO;
import br.com.sp.pratica.dtos.UserDTO;
import br.com.sp.pratica.dtos.postDtos.PostToDoList;
import br.com.sp.pratica.dtos.putDtos.PutToDoList;

public interface ModelMapperService {
	
	UserDTO convertUserToUserDTO(User user);

	User converUserDTO_to_User(UserDTO userDTO);

	ToDoListDTO convertToDoListToDTO(ToDoList to_do_list);

	ToDoList convertPostToDoListToToDoList(PostToDoList toDoList);

	ToDoList convertPutToDoListToTodoList(PutToDoList putToDoList);

}
