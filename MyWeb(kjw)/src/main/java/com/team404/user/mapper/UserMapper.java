package com.team404.user.mapper;

import com.team404.command.UserVO;

public interface UserMapper {

	public int idConfirm(UserVO	vo);//아이디 중복체크
	public int joinForm(UserVO vo);
	public int login(UserVO vo);
	public UserVO getInfo(String userId);
	public int update(UserVO vo);
	
}
