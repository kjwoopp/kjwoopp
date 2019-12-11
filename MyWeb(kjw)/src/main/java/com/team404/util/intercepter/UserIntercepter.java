package com.team404.util.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UserIntercepter extends HandlerInterceptorAdapter {

	//세션에 user_id가 없는 경우만 실행 
	public void saveURI(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String query = request.getQueryString(); // 요청정보중에 매개값을 받음
		System.out.println(uri);
		System.out.println(query);
		
		HttpSession session = request.getSession();
		session.setAttribute("uri", uri + (query == null ? "" : "?"+query)); //절대경로
		
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		String user_id = (String)session.getAttribute("user_id");
		
		if (user_id == null) {
			saveURI(request); //주소에 대한 정보를 얻음 
			response.sendRedirect(request.getContextPath()+"/user/userLogin");
			return false;
		}
		else {
			return true;
		}
		
		
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		super.postHandle(request, response, handler, modelAndView);
	}

	
	
}
