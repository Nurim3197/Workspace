package homework.todo.service;

import homework.todo.vo.TodoListVO;
import homework.todo.vo.WriteTodoVO;

public interface TodoService {
	
	public TodoListVO getAllTodo();
	
	public Boolean createNewTodo(WriteTodoVO writeTodoVO);
	
}
