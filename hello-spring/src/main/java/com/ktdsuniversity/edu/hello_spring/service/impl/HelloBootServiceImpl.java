package com.ktdsuniversity.edu.hello_spring.service.impl;

import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.hello_spring.service.HelloBootService;

@Service
public class HelloBootServiceImpl implements HelloBootService{

	
	
	@Override
	public String getGreetingMessage() {
		return "안녕하세요, 서비스 클래스입니다.";
	}
	public HelloBootServiceImpl() {
		System.out.println("HelloBootServiceImpl 인스턴스 생성함.");
	}
	
}
