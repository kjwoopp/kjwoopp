package com.team404.util.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class FreeBoardIntercepter extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("보드 인터셉트 연결 성공");
		
		//1.글등록 에는 등록을 클릭했을때, 로그인 인터셉터 연결
		//2.Regist화면에는 작성자를 세션값 처리,  작성자 readonly
		//3.수정, 변경, 삭제 클릭시 인터셉터 실행시킴(단, 화면에서는 writer대한 정보를 반드시 념겨줘야됨 ) 
		String writer = request.getParameter("writer");
		String user_id = (String)request.getSession().getAttribute("user_id");
		
		if (writer != null && writer.equals(user_id)) { // 작성자가  null이 아니고, 세션과 작성자가 같으면 컨트롤러 실행
			return true;
		}else {
			response.setContentType("text/html"); //응답내용에 대한 내용
			response.setCharacterEncoding("utf-8");//응답내용에 대한 한글 처리
			response.getWriter().write("<script>");
			response.getWriter().write("alert('권한이 없습니다');");
			response.getWriter().write("history.go(-1);");
			response.getWriter().write("</script>");
			
			return false;
		}
		
		
		
	}

	
	
	
}
