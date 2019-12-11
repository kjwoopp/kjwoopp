package com.team404.controllerTest;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.team404.command.TestVO;

@RestController
@RequestMapping("/restControl")
public class RestBasicController {
	
	//1.RestController는 기본적으로 return에 실린값이  리졸버 뷰로 전달되는 것이아닌
	//요청된 주소로 반환합니다
	
	//나는 문자열로 반환 보낼게
	@RequestMapping(value = "/getText", produces = "text/plain; charset =utf-8")
	public String getText() {
		return "안녕하세요";
	}

	//나는 객체로 반환 보낼게(단, JSON-DataBind 라이브러리가 필요)	
	@RequestMapping(value = "/getObject")
	public TestVO getObject() {
		
		TestVO vo = new TestVO(20,"홍홍홍","kkk123");
		return vo;
	}
	
	//나 num을 받고, 컬렉션보냄
	@RequestMapping(value = "/getCollection")
	public ArrayList<TestVO> getCollection(@RequestParam("num") int num){
		
		ArrayList<TestVO> list = new ArrayList<>();
		for (int i = 0; i <= num; i++) {
			TestVO vo =new TestVO(i,"홍홍"+i,"kkk"+i);
			list.add(vo);
		}
		return list;
	}
	
	
	//String 으로 값을 2개받고 Map형태로 반환할게
	@RequestMapping(value = "/getPath/{id}/{pw}")
	public HashMap<String, TestVO> getPath(@PathVariable("id") String id,
										   @PathVariable("pw") String pw
										   ){
		
		System.out.println(id);
		System.out.println(pw);
		
		HashMap<String, TestVO> map = new HashMap<>();
		map.put("info", new TestVO(10,"홍길순","hh123"));
		
		return map;
	}
	
	//@RequestBody json객체를 자바 vo에 자동 맵핑한다.
	//구글 확장프로그램 rest client 설치
	@RequestMapping(value = "/getJson")
	public ArrayList<TestVO> getJson(@RequestBody TestVO vo, HttpServletRequest request){
		
		ArrayList<TestVO> list = new ArrayList<>();
		list.add(new TestVO(20,"호오오옹","lll123"));
		
		System.out.println("요청주소: "+ request.getRemoteAddr());
		
		return list;
	}
	

	
	//REST API에서 getMember/값1/값2의 url로 호출하여
	//두값이 동일 하다면 TestVO에 (40,값1,홍길동)을 담아 반환하고 그렇지 않으면 null반환하는 메소드 생성
	//rest client에서 확인
	
	@RequestMapping(value = "/getMember/{num1}/{num2}")
	public HashMap<String, TestVO> getMember(@PathVariable("num1") String num1,
											@PathVariable("num2") String num2											
											){
		if (num1.equals(num2)) {
			HashMap<String, TestVO> map = new HashMap<>();
			map.put("result",new TestVO(40,num1,"홍길자"));
			return map;
		}
		else {
			return null;
		}
	}
	
	
	
	
	
	
	
	
}
