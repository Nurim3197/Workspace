package homework.member.service;

import homework.member.vo.LoginMemberVO;
import homework.member.vo.MemberVO;
import homework.member.vo.RegistMemberVO;

public interface MemberService {
	
	public boolean checkAvailableEmail(String email);
	
	public boolean registNewMember(RegistMemberVO registMemberVO);
	
	public MemberVO readMember(LoginMemberVO loginMemberVO); 
	
}
