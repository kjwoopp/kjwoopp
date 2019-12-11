package com.team404.util;

import java.util.ArrayList;

import com.team404.command.ReplyVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplyPageVO {

	private ArrayList<ReplyVO> list; //1번 변수
	private int replyCount; //2번 변수
	
	
	
	
}
