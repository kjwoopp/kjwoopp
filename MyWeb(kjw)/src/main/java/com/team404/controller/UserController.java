package com.team404.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team404.command.UserVO;
import com.team404.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	//회원가입 화면
	@RequestMapping("/userJoin")
	public String userJoin() {
		return "user/userJoin";
	}
	
	@RequestMapping("/userLogin")
	public String userLogin() {
		
		return "user/userLogin";
	}
	
	//마이페이지 화면(페이지 진입시 ,조인을 통해서, user에대한 정보와,user가 쓴글에대한 정보를 동시에 처리)
	 @RequestMapping("/userMypage") 
	 public String userMypage(HttpSession session, Model model) {
		 
		 String userId = (String)session.getAttribute("user_id");
		 UserVO userVO = userService.getInfo(userId);//join의 결과를 한번에 가져온다
		 System.out.println(userVO.toString());
		 
		 model.addAttribute("userVO", userVO);
		 
		 return "user/userMypage"; 
	 }
	 
	
	
	// 일반 컨트롤러에서 @ResponseBody어노테이션을 적어주면 메서드의 리턴값을
	// 뷰리졸버로 가지 않고 해당메서드를 호출한곳으로 반환합니다.
	@RequestMapping("/idConfirm")
	@ResponseBody
	public int idConfirm(@RequestBody UserVO vo) {
		System.out.println("ss"+vo.toString());
		int result = userService.idConfirm(vo);
		System.out.println(result);
		
		return result;
	}
	
	
	
	@RequestMapping(value = "/joinForm", method = RequestMethod.POST)
	public String joinForm(UserVO vo, RedirectAttributes RA ) {
	
		//join메서드로 insert작업
		//1.성공시 login페이지로 유도
		//2.실패시 login페이지 유도
		System.out.println(vo.toString());
		int result = userService.joinForm(vo);
		System.out.println(result);
		
		if (result == 1) {
			RA.addFlashAttribute("msg","회원가입 축하합니다.");
		}else {
			RA.addFlashAttribute("msg","회원가입 실패했습니다.");
		}
		return "redirect:/user/userLogin";

	}
	
	@RequestMapping(value = "/loginForm", method = RequestMethod.POST)
	public String loginForm(UserVO vo, RedirectAttributes RA, HttpSession session) {
	
		int result = userService.login(vo);
		/*
		 * if (result == 1) {
		 * 
		 * session.setAttribute("user_id", vo.getUserId());//세션에 아이디저장 return
		 * "redirect:/"; // 홈화면
		 * 
		 * }else { RA.addFlashAttribute("msg","로그인 실패."); return
		 * "redirect:/user/userLogin"; }
		 */

		//postHandler와 접목 시켜서 사용, 단 중복 리다이렉트 이동이 일어나면 안됩니다.
		if (result == 1) {
			
			session.setAttribute("user_id", vo.getUserId());//세션에 아이디저장
			return "home"; // 홈화면
			
		}else {
			RA.addFlashAttribute("msg","로그인 실패.");
			return "redirect:/user/userLogin";
		}

	}
	
	@RequestMapping(value = "/userLogout")
	public String userLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
		

	}
	
	@RequestMapping("/updateUser")
	@ResponseBody
	public int updateUser(@RequestBody UserVO vo) {
		
		System.out.println(vo.toString());
		
		int result = userService.update(vo);
		
		return result;
	}
	

	
	
	
	
	
	
	
	
	
}
