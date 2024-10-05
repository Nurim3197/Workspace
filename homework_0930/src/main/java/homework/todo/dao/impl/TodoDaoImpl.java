package homework.todo.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import homework.todo.dao.TodoDao;
import homework.todo.vo.TodoVO;
import homework.todo.vo.WriteTodoVO;

@Repository
public class TodoDaoImpl extends SqlSessionDaoSupport implements TodoDao {
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		System.out.println("SqlSessionTemplate Autowired 완료");
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public int selectTodoAllCount() {
		return this.getSqlSession().selectOne("homework.todo.dao.TodoDao.selectTodoAllCount");
	}
	
	@Override
	public List<TodoVO> selectAllTodo() {
		return this.getSqlSession().selectList("homework.todo.dao.TodoDao.selectAllTodo");
	}
	
	@Override
	public int insertNewTodo(WriteTodoVO writeTodoVO) {
		return this.getSqlSession().insert("homework.todo.dao.TodoDao.insertNewTodo", writeTodoVO);
	}
	
}
