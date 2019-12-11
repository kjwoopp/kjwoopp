package com.team404.freereply.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team404.command.ReplyVO;
import com.team404.freereply.mapper.FreeReplyMapper;
import com.team404.util.Criteria;
import com.team404.util.ReplyPageVO;

@Service("freereplyService")
public class FreeReplyServiceImpl implements FreeReplyService{
	
	@Autowired
	FreeReplyMapper freereplyMapper;
	
	@Override
	public int regist(ReplyVO vo) {
		
		return freereplyMapper.regist(vo);
	}

	/*
	 * @Override public ArrayList<ReplyVO> getList(int bno) {
	 * 
	 * return freereplyMapper.getList(bno); }
	 */
	
	@Override//더보기
	public ReplyPageVO getList(Criteria cri, int bno) {
		
		//1. 페이징된 List구해옴
		//2.전체 게시글 수를 구함(각각 다른 2개의 데이터를 마이바티스 전송할때 @Param어노테이션 사용)
		//3. list와 replyCount를 vo에저장
		ArrayList<ReplyVO> list = freereplyMapper.getList(cri, bno);
		int replyCount = freereplyMapper.getTotal(bno);
		ReplyPageVO vo = new ReplyPageVO(list,replyCount);
		
		return vo;
	}

	
	@Override
	public int pwCheck(ReplyVO vo) {
		
		return freereplyMapper.pwCheck(vo);
	}

	@Override
	public int delete(ReplyVO vo) {
		
		return freereplyMapper.delete(vo);
	}

	@Override
	public int update(ReplyVO vo) {
		
		return freereplyMapper.update(vo);
	}


	
	
}
