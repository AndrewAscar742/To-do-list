package br.com.sp.pratica.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.sp.pratica.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	UserDetails findByEmail(String username);
	
	@Query("SELECT u FROM User u WHERE u.enabled = true")
	Page<User> listAllEnabled(Pageable pageable);
	
	@Query("SELECT u FROM User u WHERE u.enabled = false")
	Page<User> listAllDisabled(Pageable pageable);

}
