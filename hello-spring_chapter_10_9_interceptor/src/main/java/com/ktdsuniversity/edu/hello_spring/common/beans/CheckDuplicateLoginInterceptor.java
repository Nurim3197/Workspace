package com.ktdsuniversity.edu.hello_spring.common.beans;

import org.springframework.web.servlet.HandlerInterceptor;

import com.ktdsuniversity.edu.hello_spring.member.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * 로그인 되어있는 상태에서 로그인페이지에 접근하면
 * /board/list로 이동시키는 인터셉터
 */
public class CheckDuplicateLoginInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request,
							HttpServletResponse response,
							Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		UserVO memberVO = (UserVO)session.getAttribute("_LOGIN_USER_");
		if(memberVO != null) {
			response.sendRedirect("/board/list");
			
			return false;
		}
		return true;
	}

}
