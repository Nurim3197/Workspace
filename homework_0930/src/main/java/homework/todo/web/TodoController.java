package homework.todo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import homework.todo.service.TodoService;
import homework.todo.vo.TodoListVO;
import homework.todo.vo.WriteTodoVO;
import jakarta.validation.Valid;

@Controller
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@GetMapping("/todo/list")
	public String viewTodoList(Model model) {
		
		TodoListVO todoListVO = this.todoService.getAllTodo();
		
		model.addAttribute("todoListVO", todoListVO);
		
		return "todo/listtodo";
	}
	
	@GetMapping("/todo/write")
	public String viewTodoWritePage() {
		return "todo/writetodo";
	}
	
	@PostMapping("/todo/write")
	public String createNewTodo(@Valid WriteTodoVO writeTodoVO, BindingResult bindingResult,  Model model) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("writeTodoVO", writeTodoVO);
			return "/todo/writetodo";
		}
		boolean isCreate = this.todoService.createNewTodo(writeTodoVO);
		return "redirect:/todo/list";
	}
	
	@GetMapping("/todo/delete/{id}")
	public String deleteOneTodo(@PathVariable int id) {
		boolean isDelete = this.todoService.deleteOneTodo(id);
		
		if(!isDelete) {
			System.out.println("삭제에 실패했습니다. 삭제 목표 ID : " + id );
		}
		return "redirect:/todo/list";
	}
	
	@GetMapping("todo/complete/{id}")
	public String switchComplete(@PathVariable int id) {
		boolean isUpdate = this.todoService.switchComplete(id);
		if (!isUpdate) {
			System.out.println("완료처리에 실패했습니다. 목표 ID : " + id);
		}
		return "redirect:/todo/list";
	}
	
}