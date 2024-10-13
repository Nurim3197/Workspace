package homework.member.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginMemberVO {
	
	@NotBlank(message ="이메일을 입력해주세요.")
	@Email(message ="이메일의 형식을 맞춰 입력해주세요.")
	private String email;
	
	@NotBlank(message ="비밀번호를 입력해주세요.")
	private String password;
	
	private String salt;
	
	private String ip;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}	
	
	
	
}
