package br.com.sp.pratica.services.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public Page<UserDTO> listAllEnabled(Pageable pageable) {
		Page<UserDTO> page = userRepository.listAllEnabled(pageable).map(user -> modelMapperService.convertUserToUserDTO(user));
		
		return page;
	}
	
	@Override
	public Page<UserDTO> listAllDisabled(Pageable pageable) {
		Page<UserDTO> page = userRepository.listAllDisabled(pageable)
				.map(user -> modelMapperService.convertUserToUserDTO(user));

		return page;
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
		user.setEnabled(true);
		user = userRepository.save(user);
		
		return modelMapperService.convertUserToUserDTO(user);
	}
	
	@Override
	public UserDTO update(UserDTO userDTO) {
		User user = this.findUser(userDTO.getId());
		
		user = this.merge(userDTO, user);
		userRepository.saveAndFlush(user);
		
		return modelMapperService.convertUserToUserDTO(user);
	}
	
	private User merge(UserDTO userDTO, User user) {
		user.setName(userDTO.getName());
		user.setAge(userDTO.getAge());
		user.setGenre(userDTO.getGenre());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setRole(userDTO.getUserRole());
		user.setData_edition(new Date());
		
		return user;
		
	}

	@Override
	public boolean delete(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			user.setEnabled(false);
			userRepository.saveAndFlush(user);
			return true;
		} else {
			return false;
		}
		
		
		
	}
}
