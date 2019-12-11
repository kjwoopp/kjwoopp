package com.team404.freereply.service;

import java.util.ArrayList;

import com.team404.command.ReplyVO;
import com.team404.util.Criteria;
import com.team404.util.ReplyPageVO;

public interface FreeReplyService {

	public int regist(ReplyVO vo); //댓글 등록
	//public ArrayList<ReplyVO> getList(int bno); //목록처리
	public ReplyPageVO getList(Criteria cri,int bno); //더보기 목록처리
	
	public int pwCheck(ReplyVO vo);
	public int delete(ReplyVO vo);
	public int update(ReplyVO vo);
	
}
