package com.ktdsuniversity.edu.hello_spring.member.service;

import com.ktdsuniversity.edu.hello_spring.member.vo.LoginMemberVO;
import com.ktdsuniversity.edu.hello_spring.member.vo.RegistMemberVO;
import com.ktdsuniversity.edu.hello_spring.member.vo.UserVO;


public interface MemberService {
	
	public boolean registNewMember(RegistMemberVO registMemberVO);
	
	public boolean checkAvailableEmail(String email);
	
	public UserVO readMember(LoginMemberVO loginMemberVO);
	
	
}
