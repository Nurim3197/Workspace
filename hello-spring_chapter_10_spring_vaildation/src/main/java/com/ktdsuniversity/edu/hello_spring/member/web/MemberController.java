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
import com.ktdsuniversity.edu.hello_spring.member.vo.RegistMemberVO;

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
	
	


















}