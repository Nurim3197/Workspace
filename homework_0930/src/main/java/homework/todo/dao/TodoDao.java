package homework.todo.dao;

import java.util.List;

import homework.todo.vo.TodoVO;
import homework.todo.vo.WriteTodoVO;

public interface TodoDao {
	
	public int selectTodoAllCount();
	
	public List<TodoVO> selectAllTodo();
	
	public int insertNewTodo(WriteTodoVO writeTodoVO);
	
	
	
}
