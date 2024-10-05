package homework.todo.service;

import homework.todo.vo.TodoListVO;
import homework.todo.vo.WriteTodoVO;

public interface TodoService {
	
	public TodoListVO getAllTodo();
	
	public Boolean createNewTodo(WriteTodoVO writeTodoVO);
	
	public Boolean deleteOneTodo(int id);
	
	public Boolean switchComplete(int id);
}
