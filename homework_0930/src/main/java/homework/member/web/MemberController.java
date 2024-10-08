package homework.member.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import homework.common.access.dao.AccessLogDao;
import homework.common.utils.RequestUtil;
import homework.member.service.MemberService;
import homework.member.vo.LoginMemberVO;
import homework.member.vo.MemberVO;
import homework.member.vo.RegistMemberVO;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/member/regist")
	public String viewRegist() {
		return "member/regist";
	}
	
	@PostMapping("/member/regist")
	public String registNewMember(@Valid RegistMemberVO registMemberVO, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("registMemberVO", registMemberVO);
			return "member/regist";
		}
		if (!registMemberVO.isEqualsPassword()) {
			model.addAttribute("registMemberVO", registMemberVO);
			return "member/regist";
		}
		model.addAttribute("registMemberVO", registMemberVO);
		this.memberService.registNewMember(registMemberVO);
		return "redirect:/todo/list";
	}	
	
	@ResponseBody
	@GetMapping("/member/regist/available")
	public Map<String, Object> doCheckAvailableEmail(@RequestParam String email) {
		boolean isAvailableEmail = this.memberService.checkAvailableEmail(email);
		Map<String, Object> response = new HashMap<String, Object>();
		if(isAvailableEmail) {
			response.put("email", email);
			response.put("available", isAvailableEmail);
			return response;
		}
		return null;
	}
	
	
	@GetMapping("/member/login")
	public String viewLogin() {
		return "member/login";
	}
	
	@PostMapping("/member/login")
	public String doLogin(@Valid LoginMemberVO loginMemberVO,BindingResult bindingResult, 
			HttpSession session, Model model) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("loginMemberVO", loginMemberVO);
			return "member/login";
		}
		
		try {
			MemberVO memberVO = this.memberService.readMember(loginMemberVO);
			session.setAttribute("_LOGIN_USER_", memberVO);
		} catch (IllegalArgumentException iae) {
			model.addAttribute("loginMemberVO", loginMemberVO);
			model.addAttribute("message", iae.getMessage());
			return "member/login";
		}
		return "redirect:/todo/list";
	}
		
	
	
	
	
}
