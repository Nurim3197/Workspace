package homework.member.dao;

import homework.member.vo.LoginMemberVO;
import homework.member.vo.MemberVO;
import homework.member.vo.RegistMemberVO;

public interface MemberDao {
	
	public String NAMESPACE = "homework.member.dao.MemberDao";
	
	public int selectEmailCount(String email);
	
	public int insertNewMember(RegistMemberVO registMemberVO);
	
	public String selectSalt(String email);
	
	public MemberVO selectOneMember(LoginMemberVO loginMemberVO);
	
	public int updateLoginFailState(LoginMemberVO loginMemberVO);
	
	public int updateLoginSuccessState(LoginMemberVO loginMemberVO);
	
}
