package br.com.sp.pratica.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.sp.pratica.domain.User;
import br.com.sp.pratica.dtos.UserDTO;

public interface UserService {

	Page<UserDTO> listAllEnabled(Pageable pageable);

	UserDTO findById(Long id);

	boolean delete(Long id);

	UserDTO save(UserDTO userDTO);

	UserDTO update(UserDTO userDTO);

	User findUser(Long user_id);

	Page<UserDTO> listAllDisabled(Pageable pageable);

}
