package com.team404.command;



import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//롬복 라이브러리 추가시 @Data 어노테이션 게터 세터 toString() 자동생성
//@AllArgsConstructor 어노테이션은 모든 변수를 초기화 하는 생성자
//@NoArgsConstructor 어노테이션은 기본생성자
@Data
public class FreeBoardVO {

	private int bno;
	private String writer;
	private String title;
	private String content;
	private Timestamp regdate;
	private Timestamp updatedate;
	
	
	
}
