package com.ktdsuniversity.edu.hello_spring.member.vo;

public class UserVO {

	private String email;
	private String password;
	private String name;
	private String salt;
	
	private String loginFailCount;
	private String latestLoginFailDate;
	private String latestLoginSuccessDate;
	private String ip;
	
	
	
	public String getLoginFailCount() {
		return loginFailCount;
	}
	public void setLoginFailCount(String loginFailCount) {
		this.loginFailCount = loginFailCount;
	}
	public String getLatestLoginFailDate() {
		return latestLoginFailDate;
	}
	public void setLatestLoginFailDate(String latestLoginFailDate) {
		this.latestLoginFailDate = latestLoginFailDate;
	}
	public String getLatestLoginSuccessDate() {
		return latestLoginSuccessDate;
	}
	public void setLatestLoginSuccessDate(String latestLoginSuccessDate) {
		this.latestLoginSuccessDate = latestLoginSuccessDate;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
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
