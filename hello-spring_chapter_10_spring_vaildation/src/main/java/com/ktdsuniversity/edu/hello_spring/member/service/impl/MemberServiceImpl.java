package com.ktdsuniversity.edu.hello_spring.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.hello_spring.member.dao.MemberDao;
import com.ktdsuniversity.edu.hello_spring.member.service.MemberService;
import com.ktdsuniversity.edu.hello_spring.member.vo.RegistMemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public boolean registNewMember(RegistMemberVO registMemberVO) {
		int insertCnt = this.memberDao.insertNewMember(registMemberVO);
		return insertCnt > 0;
	}
	
	@Override
	public boolean checkAvailableEmail(String email) {
		return this.memberDao.selectEmailCount(email) == 0;
	}
	
	
	
}
