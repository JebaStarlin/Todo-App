package com.example.todoapp.todo;

import java.time.LocalDate;
import java.util.List;

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

//@Controller
@SessionAttributes("name")
public class TodoController {
	@Autowired
	private TodoService todoService;
	
	@RequestMapping("list-todos")
	public String listTodosPage(ModelMap model) {
		String username= getLoggedInUsername(model);
		List<Todo> todos= todoService.findByUsername(username);
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
		todoService.addTodo(getLoggedInUsername(model), todo.getDescription(), todo.getDate(), false);
		return "redirect:list-todos";
	}
	
	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) {
		todoService.deleteById(id);
		return "redirect:list-todos";
	}
	
	@RequestMapping(value="update-todo",method=RequestMethod.GET)
	public String showUpdateTodo(@RequestParam int id,ModelMap model) {
		Todo todo= todoService.findById(id);
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
		todoService.updateTodo(todo);
		return "redirect:list-todos";
	}
	
}	
