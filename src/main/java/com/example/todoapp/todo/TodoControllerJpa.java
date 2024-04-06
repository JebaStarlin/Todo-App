package com.example.todoapp.todo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {
	
	@Autowired
	private TodoRepository todoRepository;
	
	@RequestMapping("list-todos")
	public String listTodosPage(ModelMap model) {
		String username= getLoggedInUsername(model);
		List<Todo> todos= todoRepository.findByUsername(username);
		model.put("todos",todos);
		return "listTodos";
	}

	private String getLoggedInUsername(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	
	@RequestMapping(value="add-todo",method=RequestMethod.GET)
	public String addTodoPage(ModelMap model) {
		Todo todo=new Todo(0,getLoggedInUsername(model),"",LocalDate.now(),false);
		model.put("todo", todo);
		return "todo";
	}
	
	@RequestMapping(value="add-todo",method=RequestMethod.POST)
	public String showTodoPage(ModelMap model,@Valid Todo  todo,BindingResult result) {
		if(result.hasErrors()) {
			return "todo";
		}
		todo.setUsername(getLoggedInUsername(model));
		todoRepository.save(todo);
//		todoService.addTodo(getLoggedInUsername(model), todo.getDescription(), todo.getDate(), todo.getIsDone());
		return "redirect:list-todos";
	}
	
	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) {
		todoRepository.deleteById(id);
		//todoService.deleteById(id);
		return "redirect:list-todos";
	}
	
	@RequestMapping(value="update-todo",method=RequestMethod.GET)
	public String showUpdateTodo(@RequestParam int id,ModelMap model) {
		Optional<Todo> todo= todoRepository.findById(id);
		model.addAttribute("todo", todo);
		return "todo";
	}
	
	@RequestMapping(value="update-todo",method=RequestMethod.POST)
	public String updateTodoPage(ModelMap model,@Valid Todo  todo,BindingResult result) {
		if(result.hasErrors()) {
			return "todo";
		}
		String username=getLoggedInUsername(model);
		todo.setUsername(username);
		todoRepository.save(todo);
//		todoService.updateTodo(todo);z
		return "redirect:list-todos";
	}
	
}	
