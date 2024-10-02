package com.ktdsuniversity.edu.hello_spring.bbs.vo;

import org.springframework.web.multipart.MultipartFile;

public class WriteBoardVO {
	
	private String subject;
	private String email;
	private String content;
	
	private String fileName;
	private String originFileName;
	
	private MultipartFile file;
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
		
		if(this.subject != null) {
			this.subject = this.subject.trim();
			this.subject = this.subject.replace("<", "&lt");
			this.subject = this.subject.replace(">", "&gt");
		}
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
		
		if(this.email != null) {
			this.email = this.email.trim();
			this.email = this.email.replace("<", "&lt");
			this.email = this.email.replace(">", "&gt");
		
		}
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
		
		if(this.content != null) {
			this.content = this.content.trim();
			this.content = this.content.replace("<", "&lt");
			this.content = this.content.replace(">", "&gt");
		}
	}
	public String getOriginFileName() {
		return originFileName;
	}
	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
