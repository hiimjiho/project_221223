package com.PersonalProject.user.dao;

import org.springframework.stereotype.Repository;

import com.PersonalProject.user.model.User;

@Repository
public interface UserMapper {
	public User selectUserByLoginId(String loginId);
	
	public User selectUserByNickname(String nickname);
}
