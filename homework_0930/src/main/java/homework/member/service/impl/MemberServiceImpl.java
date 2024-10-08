package homework.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import homework.common.access.dao.AccessLogDao;
import homework.common.access.vo.AccessLogVO;
import homework.common.beans.Sha;
import homework.common.utils.RequestUtil;
import homework.member.dao.MemberDao;
import homework.member.service.MemberService;
import homework.member.vo.LoginMemberVO;
import homework.member.vo.MemberVO;
import homework.member.vo.RegistMemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private AccessLogDao accessLogDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private Sha sha;
	
	@Override
	public boolean checkAvailableEmail(String email) {
		return this.memberDao.selectEmailCount(email) == 0;
	}
	
	@Override
	public boolean registNewMember(RegistMemberVO registMemberVO) {
		String salt = sha.generateSalt();
		
		String password = registMemberVO.getPassword();
		password = sha.getEncrypt(password, salt);
		
		registMemberVO.setPassword(password);
		registMemberVO.setSalt(salt);
		
		int insertCount = this.memberDao.insertNewMember(registMemberVO);
		return insertCount > 0;
	}
	
	@Override
	public MemberVO readMember(LoginMemberVO loginMemberVO) {
		
		loginMemberVO.setIp(RequestUtil.getIp());
		
		boolean isBlock = this.accessLogDao.selectLoginFailCount(loginMemberVO.getIp()) >= 5;
		if (isBlock) {
			throw new IllegalArgumentException("5번 이상 시도하셨습니다. 마지막 시도로부터 1시간 뒤 실행해주세요.");
		}
		
		
		String salt = this.memberDao.selectSalt(loginMemberVO.getEmail());
		if (salt == null) {
			AccessLogVO accessLogVO = new AccessLogVO();
			
			// 해당하는 이메일이 없기때문에 넣으면 안됨, 계정을 만들지도 않았는데 기록이 남아있으면 나중에 아이디 만들면 문제가 생길 수 있음
//			accessLogVO.setAccessEmail(loginMemberVO.getEmail()); 
			
			accessLogVO.setAccessIp(loginMemberVO.getIp());
			accessLogVO.setAccessType("LOGIN");
			accessLogVO.setAccessUrl(RequestUtil.getRequest().getRequestURI());
			accessLogVO.setAccessMethod(RequestUtil.getRequest().getMethod().toUpperCase());
			this.accessLogDao.insertNewAccessLog(accessLogVO);
			throw new IllegalArgumentException("아이디 또는 비밀번호가 올바르지 않습니다.");
		}
		
		String password = loginMemberVO.getPassword();
		password = this.sha.getEncrypt(password, salt);
		loginMemberVO.setPassword(password);
		
		MemberVO memberVO = this.memberDao.selectOneMember(loginMemberVO);
		
		
		if (memberVO == null) {
			AccessLogVO accessLogVO = new AccessLogVO();
			accessLogVO.setAccessEmail(loginMemberVO.getEmail()); 
			accessLogVO.setAccessIp(loginMemberVO.getIp());
			accessLogVO.setAccessType("LOGIN");
			accessLogVO.setAccessUrl(RequestUtil.getRequest().getRequestURI());
			accessLogVO.setAccessMethod(RequestUtil.getRequest().getMethod().toUpperCase());
			this.accessLogDao.insertNewAccessLog(accessLogVO);
			this.memberDao.updateLoginFailState(loginMemberVO);
			
			
			throw new IllegalArgumentException("아이디 또는 비밀번호가 올바르지 않습니다.");
		}
		AccessLogVO accessLogVO = new AccessLogVO();
		accessLogVO.setAccessEmail(loginMemberVO.getEmail()); 
		accessLogVO.setAccessIp(loginMemberVO.getIp());
		accessLogVO.setAccessType("LOGIN");
		accessLogVO.setAccessUrl(RequestUtil.getRequest().getRequestURI());
		accessLogVO.setAccessMethod(RequestUtil.getRequest().getMethod().toUpperCase());
		accessLogVO.setLoginSuccessYn("Y");
		this.accessLogDao.insertNewAccessLog(accessLogVO);
		
		this.memberDao.updateLoginFailState(loginMemberVO);
		return memberVO;
	}
	
	
}
