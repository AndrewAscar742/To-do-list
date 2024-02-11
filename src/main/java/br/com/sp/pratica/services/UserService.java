package br.com.sp.pratica.services;

import java.util.List;

import br.com.sp.pratica.domain.User;
import br.com.sp.pratica.dtos.UserDTO;

public interface UserService {

	List<UserDTO> listAll();

	UserDTO findById(Long id);

	boolean delete(Long id);

	UserDTO save(UserDTO userDTO);

	UserDTO update(UserDTO userDTO);

	User findUser(Long user_id);

}
