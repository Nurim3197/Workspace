package com.ktdsuniversity.edu.hello_spring.member.dao;

import com.ktdsuniversity.edu.hello_spring.member.vo.LoginMemberVO;
import com.ktdsuniversity.edu.hello_spring.member.vo.RegistMemberVO;
import com.ktdsuniversity.edu.hello_spring.member.vo.UserVO;

public interface MemberDao {
	
	// static final은 interface에서 생략 가능
	public String NAMESPACE = "com.ktdsuniversity.edu.hello_spring.member.dao.MemberDao";
	
	public int insertNewMember(RegistMemberVO registMemberVO);
	
	public int selectEmailCount(String email);
	
	public String selectSalt(String email);
	
	public UserVO selectOneMember(LoginMemberVO loginMemberVO);
	
}
