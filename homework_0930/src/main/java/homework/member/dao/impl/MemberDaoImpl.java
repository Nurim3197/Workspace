package homework.member.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import homework.member.dao.MemberDao;
import homework.member.vo.LoginMemberVO;
import homework.member.vo.MemberVO;
import homework.member.vo.RegistMemberVO;

@Repository
public class MemberDaoImpl extends SqlSessionDaoSupport implements MemberDao {
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	 
	@Override
	public int selectEmailCount(String email) {
		return this.getSqlSession().selectOne(NAMESPACE + ".selectEmailCount", email);
	}
	
	@Override
	public int insertNewMember(RegistMemberVO registMemberVO) {
		return this.getSqlSession().insert(NAMESPACE+".insertNewMember", registMemberVO);
	}
	
	@Override
	public String selectSalt(String email) {
		return this.getSqlSession().selectOne(NAMESPACE+".selectSalt", email);
	}
	@Override
	public MemberVO selectOneMember(LoginMemberVO loginMemberVO) {
		return this.getSqlSession().selectOne(NAMESPACE+".selectOneMember", loginMemberVO);
	}
	
	@Override
	public int updateLoginFailState(LoginMemberVO loginMemberVO) {
		return this.getSqlSession().update(NAMESPACE+"updateLoginFailState", loginMemberVO);
	}
		
	@Override
	public int updateLoginSuccessState(LoginMemberVO loginMemberVO) {
		return this.getSqlSession().update(NAMESPACE+".updateLoginSuccessState", loginMemberVO);
	}
	
	
	
}
