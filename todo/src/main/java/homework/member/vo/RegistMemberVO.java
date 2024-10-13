package homework.member.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RegistMemberVO {
	
	@NotBlank(message="이메일을 입력해주세요.")
	@Email(message = "이메일 형식에 맞추어 입력해주세요.")
	private String email;

	@NotBlank(message ="이름을 입력해주세요.")
	private String name;
	
	@NotBlank(message="비밀번호를 입력해주세요.")
	private String password;
	
	@NotBlank(message = "비밀번호 확인란을 입력해주세요.")
	private String checkPassword;
	

	private String salt;

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

	public String getCheckPassword() {
		return checkPassword;
	}
	
	public void setCheckPassword(String checkPassword) {
			this.checkPassword = checkPassword;
	}
	
	public String getSalt() {
		return salt;
	}
	
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public boolean isEqualsPassword() {
		if(this.password.equals(this.checkPassword)) {
			return true;
		}
		return false;
	}
	
	
}
