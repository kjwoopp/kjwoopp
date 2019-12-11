package com.team404.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team404.command.ReplyVO;
import com.team404.freereply.service.FreeReplyService;
import com.team404.util.Criteria;
import com.team404.util.ReplyPageVO;

@RestController
@RequestMapping("/reply")
public class ReplyController {

	@Autowired
	FreeReplyService freereplyService;
	
	//jackson-databind라이브러리 추가 필수
	@RequestMapping("/new")
	public int regist(@RequestBody ReplyVO vo) {
		
		//서비스->매퍼->regist이름 insert작업
		//결과를 반환
		
		int result = freereplyService.regist(vo);

		System.out.println(vo.toString());
		return result;
	}
	
	//댓글목록
	@RequestMapping("/getReply/{bno}/{pageNum}")
	public ReplyPageVO getReply(@PathVariable("bno") int bno,
										@PathVariable("pageNum") int pageNum
										){
		System.out.println("bno: "+bno);
		System.out.println("pageNum: "+pageNum);
		
		//ArrayList<ReplyVO> list = freereplyService.getList(bno);
		//System.out.println(list.toString());
		
		//더보기
		//1.Criteria 클래스에 pageNum와, 들고올 게시글수 20개 세팅
		//2.getList메서드는 (cri,게시글번호) 매개변수로 받는다.
		//3.ReplyPageVO 에list와  total을 담아서 반환해준다.
		//4.화면에서는 더보기 조건처리
		Criteria cri = new Criteria(pageNum, 20);
		ReplyPageVO list = freereplyService.getList(cri,bno);

		return list;
	}
	
	//댓글 삭제
	@RequestMapping("/delete")
	public int delete(@RequestBody ReplyVO vo) {
		
		System.out.println(vo.toString());
		int result = freereplyService.pwCheck(vo); // 비밀번호가 맞다묜 1반환 , 틀리면 0
		
		if(result == 1) {
			return freereplyService.delete(vo);
		}
		else {
			return 0;
		}

	}
	
	//댓글 수정
		@RequestMapping("/update")
		public int update(@RequestBody ReplyVO vo) {
			System.out.println(vo.toString());
			
			int result = freereplyService.pwCheck(vo);
			
			if(result == 1) {
				return freereplyService.update(vo);
			}
			else {
				return 0;
			}
		}
	
	
	
	
}
