package com.ktdsuniversity.edu.hello_spring.member.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.hello_spring.member.dao.MemberDao;
import com.ktdsuniversity.edu.hello_spring.member.vo.LoginMemberVO;
import com.ktdsuniversity.edu.hello_spring.member.vo.RegistMemberVO;
import com.ktdsuniversity.edu.hello_spring.member.vo.UserVO;

@Repository
public class MemberDaoImpl extends SqlSessionDaoSupport implements MemberDao {
	
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public int insertNewMember(RegistMemberVO registMemberVO) {
		return this.getSqlSession().insert(NAMESPACE + ".insertNewMember", registMemberVO);
	}
	
	@Override
	public int selectEmailCount(String email) {
		return this.getSqlSession().selectOne("com.ktdsuniversity.edu.hello_spring.member.dao.MemberDao.selectEmailCount", email);
	}
	
	@Override
	public String selectSalt(String email) {
		return this.getSqlSession().selectOne(NAMESPACE + ".selectSalt", email);
	}
	
	@Override
	public UserVO selectOneMember(LoginMemberVO loginMemberVO) {
		return this.getSqlSession().selectOne(NAMESPACE + ".selectOneMember", loginMemberVO);
	}
	
	@Override
	public int updateLoginFailState(LoginMemberVO loginMemberVO) {
		return this.getSqlSession().update(NAMESPACE + ".updateLoginFailState", loginMemberVO);
	}
	
	@Override
	public int selectLoginImpossibleCount(String email) {
		return this.getSqlSession().selectOne(NAMESPACE+".selectLoginImpossibleCount", email);
	}
	
	@Override
	public int updateLoginSuccessState(LoginMemberVO loginMemberVO) {
		return this.getSqlSession().update(NAMESPACE +".updateLoginSuccessState", loginMemberVO);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
