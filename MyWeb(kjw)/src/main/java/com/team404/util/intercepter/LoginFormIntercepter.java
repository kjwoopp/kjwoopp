package com.team404.util.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginFormIntercepter extends HandlerInterceptorAdapter{

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		System.out.println("실행됨");
		//로그인 성공시 생성되는 세션 user_id
		String user_id = (String)request.getSession().getAttribute("user_id");
		String uri = (String)request.getSession().getAttribute("uri");
		System.out.println(uri);
		//1
		if (uri != null && user_id != null) { // 로그인 성공,기존 접근하려는 유알아이디가 있는경우uri로 이동
			response.sendRedirect(uri);
		}
		//2
		else if (user_id != null) { //일반적인 로그인 성공
			response.sendRedirect(request.getContextPath());
		}
		//3
		//로그인 실패인 경우, 기존의 컨트롤러대로 실행됨 
		
		
		
	}

	
	
}
