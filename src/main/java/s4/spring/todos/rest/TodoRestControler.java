package s4.spring.todos.rest;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import s4.spring.todos.models.Todo;
import s4.spring.todos.repository.TodoRepository;


@RestController
@RequestMapping("/todos/rest")
public class TodoRestControler {
	
	@Autowired
	private TodoRepository repo;
	
	@GetMapping("")
	public @ResponseBody List<Todo> read(){
		return repo.findAll();
	}
	
	@GetMapping("{id}")
	public @ResponseBody Todo read(@PathVariable int id){
		Optional<Todo> opt=repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		return null;
	}
	
	@PostMapping("create")
	public @ResponseBody Todo create(@RequestBody Todo orga) {
		repo.save(orga);
		return orga;
	}
	
	@PutMapping("update")
	public @ResponseBody Todo update(@RequestBody Todo orga) {
		repo.save(orga);
		return orga;
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<String> delete(@PathVariable int id) {
		repo.deleteById(id);
		return new ResponseEntity<String>("Suppression réussie!", HttpStatus.OK);
	}

	
	
}
