package br.com.sp.pratica.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.sp.pratica.domain.User;
import br.com.sp.pratica.dtos.UserDTO;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	UserDetails findByEmail(String username);
	
	@Query("SELECT u FROM User u WHERE u.email = :email")
	Optional<User> validarEmail(@Param("email") String email);
	
	@Query("SELECT u FROM User u WHERE u.enabled = true")
	Page<User> listAllEnabled(Pageable pageable);
	
	@Query("SELECT u FROM User u WHERE u.enabled = false")
	Page<User> listAllDisabled(Pageable pageable);


	

}
