package com.team404.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //게터,세터
@AllArgsConstructor//모든변수 초기화
@NoArgsConstructor //기본생성자
public class TestVO {

	private int age;
	private String name;
	private String id;
	
}
