package homework.todo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import homework.todo.dao.TodoDao;
import homework.todo.service.TodoService;
import homework.todo.vo.TodoListVO;
import homework.todo.vo.TodoVO;
import homework.todo.vo.WriteTodoVO;

@Service
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	private TodoDao todoDao;
	
	@Override
	public TodoListVO getAllTodo() {
		// todolist에 넣기 위한 카운트
		int todoCnt = this.todoDao.selectTodoAllCount();
		
		List<TodoVO> todoList = this.todoDao.selectAllTodo();
		
		TodoListVO todoListVO = new TodoListVO();
		todoListVO.setTodoCnt(todoCnt);
		todoListVO.setTodoList(todoList);
		
		return todoListVO;
	}
	
	@Override
	public Boolean createNewTodo(WriteTodoVO writeTodoVO) {
		int createCount = this.todoDao.insertNewTodo(writeTodoVO);
		return createCount > 0;
	}
	
}
