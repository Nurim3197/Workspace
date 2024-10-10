package com.ktdsuniversity.edu.hello_spring.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.hello_spring.access.dao.AccessLogDao;
import com.ktdsuniversity.edu.hello_spring.access.vo.AccessLogVO;
import com.ktdsuniversity.edu.hello_spring.common.beans.Sha;
import com.ktdsuniversity.edu.hello_spring.common.utils.RequestUtil;
import com.ktdsuniversity.edu.hello_spring.member.dao.MemberDao;
import com.ktdsuniversity.edu.hello_spring.member.service.MemberService;
import com.ktdsuniversity.edu.hello_spring.member.vo.LoginMemberVO;
import com.ktdsuniversity.edu.hello_spring.member.vo.RegistMemberVO;
import com.ktdsuniversity.edu.hello_spring.member.vo.UserVO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private AccessLogDao accessLogDao;
	
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
		
		boolean isIpBlock = this.accessLogDao.selectLoginFailCount( RequestUtil.getIp()) >= 5;
		if (isIpBlock) {
			throw new IllegalArgumentException("5번 이상 시도하였습니다. 1시간 후 시도해주세요.");
		}

		// 1. Salt 조회.
		String salt = this.memberDao.selectSalt(loginMemberVO.getEmail());
		if (salt == null) {
			
			AccessLogVO accessLogVO = new AccessLogVO();
			accessLogVO.setAccessType("LOGIN");
			accessLogVO.setAccessUrl(RequestUtil.getRequest().getRequestURI());
			accessLogVO.setAccessMethod(RequestUtil.getRequest().getMethod().toUpperCase());
			accessLogVO.setAccessIp(RequestUtil.getIp());
			this.accessLogDao.insertNewAccessLog(accessLogVO);
			
			throw new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다.");
		}
		
		// 2. 사용자가 입력한 비밀번호를 salt를 이용하여 암호화.
		String password = loginMemberVO.getPassword();
		password = this.sha.getEncrypt(password, salt);
		loginMemberVO.setPassword(password);
 		
		// 3. 이메일과 암호화된 비밀번호로 데이터베이스에서 회원 정보 조회.
		// LOGIN_FAIL_COUNT가 5이상 && 마지막 로그인 실패 시간이 한 시간이 지나지 않았다면
		// 이상이라면 정상적인 로그인 시도라도 하더라도 로그인은 실패시켜야 한다.
		
		
		UserVO memberVO = this.memberDao.selectOneMember(loginMemberVO);
		
		if(memberVO == null) {
			// LOGIN_FAIL_COUNT를 하나 증가시킨다.
			// LASTEST_LOGIN_FAIL_DATE를 현재 날짜로 생신한다.
			// LASTEST_LOGIN_IP를 요청자의 IP로 갱신한다.
			loginMemberVO.setIp(RequestUtil.getIp());
			AccessLogVO accessLogVO = new AccessLogVO();
			accessLogVO.setAccessEmail(loginMemberVO.getEmail()); 
			accessLogVO.setAccessIp(loginMemberVO.getIp());
			accessLogVO.setAccessType("LOGIN");
			accessLogVO.setAccessUrl(RequestUtil.getRequest().getRequestURI());
			accessLogVO.setAccessMethod(RequestUtil.getRequest().getMethod().toUpperCase());
			this.accessLogDao.insertNewAccessLog(accessLogVO);
			this.memberDao.updateLoginFailState(loginMemberVO);
			throw new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다.");
		}
		// 이걸 해도 어차피 이메일이 있는 경우에만 카운트 되므로 여러가지 생각해야한다.
		boolean isBlockState = this.memberDao.selectLoginImpossibleCount(loginMemberVO.getEmail()) > 0;
		if (isBlockState) {
			throw new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다.");
		}
		
		
		// LOGIN_FAIL_COUNT가 5이상 && 마지막 로그인 실패 시간이 한 시간이 지났다면
		// 혹은 LOGIN_FAIL_COUNT가 5미만일 경우
		
		// 정상적인 로그인 시도일 경우 로그인을 성공시킨다.
		// 이때, LOGIN_FAIL_COUNT는 0으로 초기화 시키고,
		// LATEST_LOGIN_FAIL_DATE는 NULL로 초기화
		// LATEST_LOGIN_IP는 요청자의 IP로 갱신
		// LATEST_LOGIN_SUCCES_DATE는 현재 날짜로 갱신.
		
		loginMemberVO.setIp(RequestUtil.getIp());
		this.memberDao.updateLoginSuccessState(loginMemberVO);
		
		
		AccessLogVO accessLogVO = new AccessLogVO();
		accessLogVO.setAccessType("LOGIN");
		accessLogVO.setAccessEmail(loginMemberVO.getEmail());
		accessLogVO.setAccessUrl(RequestUtil.getRequest().getRequestURI());
		accessLogVO.setAccessMethod(RequestUtil.getRequest().getMethod().toUpperCase());
		accessLogVO.setAccessIp(RequestUtil.getIp());
		accessLogVO.setLoginSuccessYn("Y");
		this.accessLogDao.insertNewAccessLog(accessLogVO);
		
		return memberVO;
	}
	
	
}
