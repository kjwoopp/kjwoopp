package com.team404.freereply.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.team404.command.ReplyVO;
import com.team404.util.Criteria;
import com.team404.util.ReplyPageVO;

public interface FreeReplyMapper {
	
	public int regist(ReplyVO vo); //댓글 등록
	//public ArrayList<ReplyVO> getList(int bno); //목록처리
	public ArrayList<ReplyVO> getList(@Param("cri") Criteria cri, @Param("bno") int bno);//더보기 목록처리
	public int getTotal(int bno); //게시글에 따른 댓글수
	
	
	public int pwCheck(ReplyVO vo);//비밀번호 확인
	public int delete(ReplyVO vo); //삭제 처리
	public int update(ReplyVO vo); //수정처리
}
