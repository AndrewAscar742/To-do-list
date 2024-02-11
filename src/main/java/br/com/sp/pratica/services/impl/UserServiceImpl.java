package br.com.sp.pratica.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.sp.pratica.domain.User;
import br.com.sp.pratica.dtos.UserDTO;
import br.com.sp.pratica.dtos.services.ModelMapperService;
import br.com.sp.pratica.repositories.UserRepository;
import br.com.sp.pratica.services.UserService;
import br.com.sp.pratica.services.exceptions.UserNotFoundException;

@Service
public class UserServiceImpl implements UserService {
	
	@Value("${USER_NOT_FOUND_EXCEPTION}")
	private final String USER_NOT_FOUND_EXCEPTION = new String();
	
	private final UserRepository userRepository;
	private final ModelMapperService modelMapperService;

	public UserServiceImpl(UserRepository userRepository, ModelMapperService modelMapperService) {
		super();
		this.userRepository = userRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public List<UserDTO> listAll() {
		List<UserDTO> list = userRepository.findAll()
				.stream()
				.map(user -> modelMapperService.convertUserToUserDTO(user))
				.toList();
		
		return list;
		
	}
	
	@Override
	public UserDTO findById(Long id) {
		UserDTO userDTO = userRepository
				.findById(id)
				.map(user -> modelMapperService.convertUserToUserDTO(user))
				.orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_EXCEPTION));
		
		return userDTO;
	}
	
	@Override
	public User findUser(Long user_id) {
		User user = userRepository.findById(user_id).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_EXCEPTION));
		
		return user;
	}
	
	@Override
	public UserDTO save(UserDTO userDTO) {
		userDTO.setDate_initial(new Date());
		
		User user = modelMapperService.converUserDTO_to_User(userDTO);
		user = userRepository.save(user);
		
		return modelMapperService.convertUserToUserDTO(user);
	}
	
	@Override
	public UserDTO update(UserDTO userDTO) {
		userDTO.setData_edition(new Date());
		
		User user = modelMapperService.converUserDTO_to_User(userDTO);
		user = userRepository.save(user);
		
		return modelMapperService.convertUserToUserDTO(user);
	}
	
	@Override
	public boolean delete(Long id) {
		Optional<User> user = userRepository.findById(id);
		
		if (user.isPresent()) {
			userRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
		
		
		
	}
}
