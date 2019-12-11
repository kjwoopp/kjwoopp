package com.team404.freeboard.mapper;

import java.util.ArrayList;

import com.team404.command.FreeBoardVO;
import com.team404.util.Criteria;

public interface FreeBoardMapper {
	
	public int registForm(FreeBoardVO vo);
	
	//public ArrayList<FreeBoardVO> getList();
	public ArrayList<FreeBoardVO> getList(Criteria cri); //기본페이징
	//public int getTotal(); //전체 게시글수
	//검색페이징
	public int getTotal(Criteria cri);
	
	public FreeBoardVO content(int bno);//상세보기
	public boolean update(FreeBoardVO vo); 
	public boolean delete(int bno);
}
