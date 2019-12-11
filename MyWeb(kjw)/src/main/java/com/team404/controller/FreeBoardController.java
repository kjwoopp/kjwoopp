package com.team404.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team404.command.FreeBoardVO;
import com.team404.freeboard.service.FreeBoardService;
import com.team404.util.Criteria;
import com.team404.util.PageVO;

@Controller
@RequestMapping("/freeBoard")
public class FreeBoardController {
	
	@Autowired
	@Qualifier("freeBoardService")
	private FreeBoardService freeBoardService;
	
	//등록화면
	@RequestMapping(value = "/freeRegist", method = RequestMethod.GET)
	public String freeRegist() {
		
		return "freeBoard/freeRegist";
	}
	
	//목록 화면
	@RequestMapping(value = "/freeList", method = RequestMethod.GET)
	public String freeList(Model model, Criteria cri) {
		//일반
		//ArrayList<FreeBoardVO> boardList =freeBoardService.getList();
		
		//기본 페이징
		//ArrayList<FreeBoardVO> boardList = freeBoardService.getList(cri);
		//int total = freeBoardService.getTotal();
		//System.out.println(total);
		//PageVO pageVO = new PageVO(cri, total);
		
		//검색페이징
		ArrayList<FreeBoardVO> boardList = freeBoardService.getList(cri);
		int total = freeBoardService.getTotal(cri);
		PageVO pageVO = new PageVO(cri, total);
		
		System.out.println("넘어오는값"+cri.toString());
		
		
		model.addAttribute("pageVO",pageVO); //페이징 정보
		model.addAttribute("boardList",boardList);//게시글 정보
		System.out.println(pageVO.toString());
		
		return "freeBoard/freeList";
	}
	
	//게시글 등록처
	@RequestMapping(value="/registForm", method = RequestMethod.POST )
	public String registForm(FreeBoardVO vo, RedirectAttributes RA) {
		System.out.println(vo.toString());
		
		//서비스의 regist메서드를 생성하고, mapper에 regist메서드를 생성한 후에
		//마이바티스 xml에서 db에 insert처리s
		freeBoardService.registForm(vo);
		RA.addFlashAttribute("msg","게시물이 정상적으로 등록되었습니다.");
		
		return "redirect:/freeBoard/freeList";
	}
	
	
//	//상세화면
//	@RequestMapping(value = "/freeDetail", method = RequestMethod.GET)
//	public String freeDetail(@RequestParam("bno") int bno , Model model) {
//		//서비스의 content 호출 mapper의 content호출
//		//모델 담아서 boardVO 이름으로 화면에 전달
//		System.out.println(bno);
//		model.addAttribute("boardVO", freeBoardService.content(bno));
//		
//		return "freeBoard/freeDetail";
//	}
//	
//	//수정화면
//	@RequestMapping(value = "/freeModify", method = RequestMethod.POST)
//	public String freeModify(@RequestParam("bno") int bno, Model model) {
//		System.out.println("수정번호: "+bno);
//
//		model.addAttribute("boardVO",freeBoardService.content(bno));
//		
//		return "freeBoard/freeModify";
//	}
	
	//상세화면, 수정화면 동일한 기능이기때문에 {}묶어서 사용
	@RequestMapping(value = {"/freeDetail", "/freeModify"})
	public void view(@RequestParam("bno") int bno , Model model) {
		
		model.addAttribute("boardVO", freeBoardService.content(bno));
		//요청의 경로로 리졸버 뷰로 전달됩니다.
	}
	
	//수정요청
	@RequestMapping(value = "/freeUpdate", method = RequestMethod.POST)
	public String freeUpdate(FreeBoardVO vo,RedirectAttributes RA) {
		//서비스의 update 호출 mapper의 update호출
		//컨트롤러에는 결과를 반환받아서 수정 성공시 RA에 msg이름으로 수정정상처리 띄우기
		// 수정실패시 msg이름으로 수정 실패 
		//화면처리 List로 이동
		
		if(freeBoardService.update(vo) == true) {
			RA.addFlashAttribute("msg","게시물이 정상적으로 수정되었습니다.");
		}else {
			RA.addFlashAttribute("msg","게시물 수정실패.");
		}
		
		
		return "redirect:/freeBoard/freeList";
	}
	
	
	@RequestMapping(value = "/freeDelete")
	public String freeDelete(@RequestParam("bno") int bno , RedirectAttributes RA) {
		
		if(freeBoardService.delete(bno) == true) {
			RA.addFlashAttribute("msg","삭제되었습니다.");
		}else {
			RA.addFlashAttribute("msg","게시물 삭제실패.");
		}
		
		return "redirect:/freeBoard/freeList";
	}
	
	
	
	
}
