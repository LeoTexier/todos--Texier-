package s4.spring.todos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import s4.spring.todos.models.Todo;



public interface TodoRepository extends JpaRepository<Todo,Integer > {
	public List<Todo> findByName(String name);
}
