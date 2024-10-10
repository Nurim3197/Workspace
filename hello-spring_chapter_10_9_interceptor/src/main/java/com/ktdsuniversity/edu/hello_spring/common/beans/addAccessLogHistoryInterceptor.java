package com.ktdsuniversity.edu.hello_spring.common.beans;

import org.springframework.web.servlet.HandlerInterceptor;

import com.ktdsuniversity.edu.hello_spring.access.dao.AccessLogDao;
import com.ktdsuniversity.edu.hello_spring.access.vo.AccessLogVO;
import com.ktdsuniversity.edu.hello_spring.member.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class addAccessLogHistoryInterceptor implements HandlerInterceptor {
	
	private AccessLogDao accessLogDao;
	
	public addAccessLogHistoryInterceptor(AccessLogDao accessLogDao) {
		this.accessLogDao = accessLogDao;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		UserVO memberVO = (UserVO) session.getAttribute("_LOGIN_USER_");
		String email = memberVO == null ? null : memberVO.getEmail();
		
		String controller = handler.toString();
		String pakageName = controller.replace("com.ktdsuniversity.edu.hello_spring.", "");
		pakageName = controller.substring(0, pakageName.indexOf(".")).toUpperCase();
		
		AccessLogVO accessLogVO = new AccessLogVO();
		accessLogVO.setAccessEmail(email);
		accessLogVO.setAccessIp(request.getLocalAddr());
		accessLogVO.setAccessMethod(request.getMethod());
		accessLogVO.setAccessType(pakageName);
		accessLogVO.setAccessUrl(request.getRequestURI());
		accessLogVO.setLoginSuccessYn(memberVO == null ? "N" : "Y");
		
		this.accessLogDao.insertNewAccessLog(accessLogVO);
		
		return true;
	}

}
