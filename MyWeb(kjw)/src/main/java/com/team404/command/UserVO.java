package com.team404.command;

import java.sql.Timestamp;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {

	private String userId;
	private String userPw;
	private String userName;
	private String userPhone1;
	private String userPhone2;
	private String userEmail1;
	private String userEmail2;
	private String addrZipNum;
	private String addrBasic;
	private String addrDetail;
	private Timestamp regDate;
	
	//마이페이지에 join을 통해 한번에 게시글에 대한정보를 사져가기위한 선언
	private ArrayList<FreeBoardVO> userBoardList;

	
}
