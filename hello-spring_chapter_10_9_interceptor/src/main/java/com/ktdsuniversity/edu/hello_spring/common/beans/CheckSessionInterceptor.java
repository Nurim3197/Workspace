package com.ktdsuniversity.edu.hello_spring.common.beans;

import org.springframework.web.servlet.HandlerInterceptor;

import com.ktdsuniversity.edu.hello_spring.member.vo.UserVO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * 요청과 응답을 가로채는 인터셉터
 */
public class CheckSessionInterceptor implements HandlerInterceptor {
	
	// handler가 controller라고 생각하면된다.
	@Override
	public boolean preHandle(HttpServletRequest request,
							HttpServletResponse response,
							Object handler)
							// 원래 실행되어야 하는 컨트롤러의 정보가 handler에 저장되어 있음
			throws Exception {
		
		/*
		 * 컨트롤러가 실행되기 전에
		 * 로그인 세션을 검사해서 로그인이 되어있지 않다면 로그인 페이지를 보여주도록 한다.
		 */
		
		// 1. 세션가져오기
		HttpSession session = request.getSession();
		
		// 2. 세션이 존재한다면 컨트롤러 실행시키기
		UserVO memberVO = (UserVO) session.getAttribute("_LOGIN_USER_");
		if (memberVO != null) {
			// 로그인을 한 경우
			return true;	// 정상적으로 컨트롤러를 수행한다.
			
		}
		
		// 3. 세션이 존재하지 않는다면 로그인 페이지 보여주기
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/member/login.jsp");
		rd.forward(request, response);
		
		return false;
	}
	
	
//	@Override
//	public void postHandle(HttpServletRequest request,
//							HttpServletResponse response,
//							Object handler,
//							ModelAndView modelAndView) throws Exception {
//		System.out.println("CheckSessionInterceptor.postHandle : " + handler);
//		System.out.println("CheckSessionInterceptor.postHandle : " + modelAndView);
//		
//	}
//	
//	@Override
//	public void afterCompletion(HttpServletRequest request,
//								HttpServletResponse response,
//								Object handler,
//								Exception ex)
//			throws Exception {
//		System.out.println("CheckSessionInterceptor.afterCompletion: " + handler);
//	}
}
