package br.com.sp.pratica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sp.pratica.domain.ToDoList;

@Repository
public interface ToDoListRepository extends JpaRepository<ToDoList, Long>{

}
