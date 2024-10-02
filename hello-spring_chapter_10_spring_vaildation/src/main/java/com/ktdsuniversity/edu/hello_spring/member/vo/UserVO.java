package com.ktdsuniversity.edu.hello_spring.member.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserVO {

	private String email;
	private String password;
	private String name;
	
	@NotBlank(message = "이메일은 필수 입력값입니다.")
	@Email(message = "이메일 형식에 맞춰서 입력해주세요.")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
		
	
	}
	
}
