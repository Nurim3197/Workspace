package com.ktdsuniversity.edu.hello_spring.member.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RegistMemberVO {
	
	private String salt;
	
	@NotBlank(message = "이메일은 필수 입력 값입니다.")
	@Email
	private String email;
	
	@NotBlank(message = "이름은 필수 입력 값입니다.")
	private String name;
	
	
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	@NotBlank(message = "패스워드를 입력해주세요.")
	private String password;
	
	@NotBlank(message = "입력한 패스워드와 같은 패스워드를 입력해주세요.")
	private String passwordCheck;
	
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
	public String getPasswordCheck() {
		return passwordCheck;
	}
	public void setPasswordCheck(String passwordCheck) {
		if(passwordCheck == null || passwordCheck.equals(this.password)) {
			this.passwordCheck = passwordCheck;
		}else {
			this.passwordCheck = null;
		}
	}
	
}
