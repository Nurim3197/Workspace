package com.ktdsuniversity.edu.hello_spring.member.web;

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

import com.ktdsuniversity.edu.hello_spring.member.service.MemberService;
import com.ktdsuniversity.edu.hello_spring.member.vo.LoginMemberVO;
import com.ktdsuniversity.edu.hello_spring.member.vo.RegistMemberVO;
import com.ktdsuniversity.edu.hello_spring.member.vo.UserVO;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/member/regist")
	public String viewMemberRegistPage() {
		return "member/regist";
	}
	
	@PostMapping("/member/regist")
	public String doRegistMember(@Valid RegistMemberVO registMemberVO, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("RegistMemberVO", registMemberVO);
			return "member/regist";
		}
		model.addAttribute("RegistMemberVO", registMemberVO);
		this.memberService.registNewMember(registMemberVO);
		return "redirect:/member/regist";
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
	
	@GetMapping("member/login")
	public String viewLogin() {
		
		/*
		 * Service에서 한 것들이다.
		 * illegalArgumentException으로 해결함
		 * 이메일 받고 salt를 가져와야한다.
		 * 1. 해당 이메일이 없는 경우
		 * 2. 성공
		 * 
		 * 받아온 패스워드로 salt를 이용하여 암호화해보고 비교한다.
		 * 1. 틀린경우 패스워드가 틀린거임
		 * 2. 맞는 경우 성공
		 */
		
		// 해커가 유추하기 쉽지않도록 "이메일이나 비밀번호를 확인해주세요"로 메시지를 띄운다.
		return "member/login";
	}
	
	@PostMapping("/member/login")
	public String doLogin(@Valid LoginMemberVO loginMemberVO, BindingResult bindingResult, HttpSession session, Model model) {

		if(bindingResult.hasErrors()) {
			model.addAttribute("loginMemberVO", loginMemberVO);
			return "member/login";
		}
		try {
		UserVO memberVO = this.memberService.readMember(loginMemberVO);
		// 로그인 상태를 서버에 저장시킨다.
		session.setAttribute("_LOGIN_USER_", memberVO);
		} catch (IllegalArgumentException iae){
			model.addAttribute("loginMemberVO", loginMemberVO);
			model.addAttribute("message", iae.getMessage());
			return "member/login";
		}
		
		
		return "redirect:/board/list";
	}
	


















}