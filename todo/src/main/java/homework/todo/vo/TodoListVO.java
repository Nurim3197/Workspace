package homework.todo.vo;

import java.util.List;

public class TodoListVO {
	/**
	 * 조회된 todo의 수
	 */
	private int todoCnt;
	
	/**
	 * 조회된 todo의 목록
	 */
	private List<TodoVO> todoList;

	public int getTodoCnt() {
		return todoCnt;
	}

	public void setTodoCnt(int todoCnt) {
		this.todoCnt = todoCnt;
	}

	public List<TodoVO> getTodoList() {
		return todoList;
	}

	public void setTodoList(List<TodoVO> todoListVO) {
		this.todoList = todoListVO;
	}
	
	
}
