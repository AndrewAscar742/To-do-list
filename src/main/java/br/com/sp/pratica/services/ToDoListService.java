package br.com.sp.pratica.services;

import java.util.List;

import br.com.sp.pratica.dtos.ToDoListDTO;
import br.com.sp.pratica.dtos.postDtos.PostToDoList;
import br.com.sp.pratica.dtos.putDtos.PutToDoList;

public interface ToDoListService {

	List<ToDoListDTO> listAll();

	ToDoListDTO save(PostToDoList toDoList);

	boolean delete(Long id);

	ToDoListDTO findById(Long id);

	ToDoListDTO update(Long id, PutToDoList putToDoList);

}
