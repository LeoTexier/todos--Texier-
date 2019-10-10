package s4.spring.todos;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.jeemv.springboot.vuejs.VueJS;
import io.github.jeemv.springboot.vuejs.utilities.Http;
import s4.spring.todos.models.Todo;
import s4.spring.todos.repository.TodoRepository;





@Controller
public class MainController {
	@Autowired
	private VueJS vue;
	
	@Autowired
	private TodoRepository repo;
	
	@RequestMapping("/todos")
	public String indexTodos(ModelMap model) {
		List<Todo> todos=repo.findAll();
		vue.addData("items",todos);
		vue.addData("dialog",false);
		vue.addDataRaw("todos","{name:'Le Nom',description:'',avancement:'',poids:'',}");
		
		vue.addMethod("addTodo", "let self=this;"+Http.post("/todos/rest/create", "this.todos", "self.dialog=false;"
				+ "self.items.push(response.data);self.todo={};"));
		vue.addMethod("updateTodo", "let self=this;self.dialog=true;self.todo=todo","todo");
		vue.addMethod("deleteTodo", 
				"let self=this;let $='';"+Http.delete("'/todos/rest/'+item.id+$","self.message=response.data;"
						+ "self.items.splice(index,1);"),"item","index");
		model.put("vue", vue);
	return "todo";
	}
}
