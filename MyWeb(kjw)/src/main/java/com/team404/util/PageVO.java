package com.team404.util;

import lombok.Data;

@Data
public class PageVO {

	private int endPage; //끝번호
	private int startPage;//처음번호
	private boolean prev;//이전버튼
	private boolean next;//다음버튼
	
	private int pageNum;
	private int amount;
	private int total;
	
	
	
	private Criteria cri;
	
	public PageVO(Criteria cri, int total) {
		
		this.cri = cri;
		this.pageNum = cri.getPageNum();
		this.amount = cri.getAmount();
		this.total= total;
	
		//전달되는 기준에 따라서 pageVO 계산처리
		//끝페이지 계산
		//현재 조회하는 페이지 12번 -> 화면에 보여질 끝페이지 20
		this.endPage =(int)Math.ceil(this.pageNum / 10.0) * 10;
		
		//시작 페이지
		this.startPage = this.endPage -10 +1;
		
		//실제페이지 마지막 페이지 계산
		//만약 게시물이 63개면? -> 페이지 번호는 7까지 포함 
		int realEnd = (int)Math.ceil(total / (double)this.amount);
		
		//ex) 152개 게시물 -> 
		// 1~10번 페이지 클릭시 -> endPage공식= 10, realEnd = 16
		// 11~16번 페이지 클릭시 -> endPage공식= 20, realEnd = 16
		if(this.endPage > realEnd) {
			this.endPage = realEnd;
		}
		
		//이전 버튼 활성화 여부
		//startPage = 1 11 21 .....
		this.prev = this.startPage > 1;
		
		//다음 버튼 활성화여부
		this.next =  realEnd > this.endPage;
		
		
	}
	
}
