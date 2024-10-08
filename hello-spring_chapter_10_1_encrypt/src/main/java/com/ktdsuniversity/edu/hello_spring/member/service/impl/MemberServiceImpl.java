package com.ktdsuniversity.edu.hello_spring.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.hello_spring.common.beans.Sha;
import com.ktdsuniversity.edu.hello_spring.member.dao.MemberDao;
import com.ktdsuniversity.edu.hello_spring.member.service.MemberService;
import com.ktdsuniversity.edu.hello_spring.member.vo.LoginMemberVO;
import com.ktdsuniversity.edu.hello_spring.member.vo.RegistMemberVO;
import com.ktdsuniversity.edu.hello_spring.member.vo.UserVO;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private Sha sha;
	
	@Override
	public boolean registNewMember(RegistMemberVO registMemberVO) {
		// 1. Salt 발급
		String salt = sha.generateSalt();
		
		// 2. 사용자의 비밀번호 암호화
		String password = registMemberVO.getPassword();
		password = sha.getEncrypt(password, salt);
		
		registMemberVO.setPassword(password);
		registMemberVO.setSalt(salt);
		
		
		int insertCnt = this.memberDao.insertNewMember(registMemberVO);
		return insertCnt > 0;
	}
	
	@Override
	public boolean checkAvailableEmail(String email) {
		return this.memberDao.selectEmailCount(email) == 0;
	}
	
	@Override
	public UserVO readMember(LoginMemberVO loginMemberVO) {

		// 1. Salt 조회.
		String salt = this.memberDao.selectSalt(loginMemberVO.getEmail());
		if (salt == null) {
			System.out.println("문제가 일어난 부분은 1");
			throw new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다.");
		}
		
		// 2. 사용자가 입력한 비밀번호를 salt를 이용하여 암호화.
		String password = loginMemberVO.getPassword();
		password = this.sha.getEncrypt(password, salt);
		loginMemberVO.setPassword(password);
 		
		// 3. 이메일과 암호화된 비밀번호로 데이터베이스에서 회원 정보 조회.
		UserVO memberVO = this.memberDao.selectOneMember(loginMemberVO);
		if(memberVO == null) {
			System.out.println("문제가 일어난 부분은 2");
			throw new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다.");
		}
		return memberVO;
	}
	
	
}
