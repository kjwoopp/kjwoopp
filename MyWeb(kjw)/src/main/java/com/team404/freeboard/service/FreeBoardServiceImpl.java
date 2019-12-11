package com.team404.freeboard.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team404.command.FreeBoardVO;
import com.team404.freeboard.mapper.FreeBoardMapper;
import com.team404.util.Criteria;

@Service("freeBoardService")
public class FreeBoardServiceImpl implements FreeBoardService{
	
	@Autowired
	private FreeBoardMapper freeBoardMapper;
	
	@Override
	public void registForm(FreeBoardVO vo) {
		 int res = freeBoardMapper.registForm(vo);
		 System.out.println(res +"성공여부");
	}

//	@Override
//	public ArrayList<FreeBoardVO> getList() {
//		
//		return freeBoardMapper.getList();
//	}

	@Override
	public ArrayList<FreeBoardVO> getList(Criteria cri) {
		
		return freeBoardMapper.getList(cri);
	}
	
	//@Override
	//public int getTotal() {
	//	
	//	return freeBoardMapper.getTotal();
	//}
	
	
	@Override
	public int getTotal(Criteria cri) {
		
		return freeBoardMapper.getTotal(cri);
	}

	@Override
	public FreeBoardVO content(int bno) {
		
		return freeBoardMapper.content(bno);
	}

	@Override
	public boolean update(FreeBoardVO vo) {
		
		return freeBoardMapper.update(vo);
	}

	@Override
	public boolean delete(int bno) {
		
		return freeBoardMapper.delete(bno);
	}


	



}
