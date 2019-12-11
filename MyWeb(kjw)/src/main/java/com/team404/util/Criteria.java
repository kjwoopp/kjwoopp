package com.team404.util;

import lombok.Data;

@Data //게터세터자동생성
public class Criteria {//DB로 전송될 클래스

	//페이지
	private int pageNum; //현재 조회하는 페이지 번호
	private int amount;  //한페이지에서 몇개의 데이터를 보여줄것인가
	
	//검색
	private String searchType; //검색타입
	private String searchName; //검색이름
	
	
	public Criteria() {//첫 페이지 진입시 생성될 생성자.
		this(1,10);
	}
	
	public Criteria(int pageNum, int amount) {//페이지 번호 클릭시 실행될 생성자
		super();
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	
	
}
