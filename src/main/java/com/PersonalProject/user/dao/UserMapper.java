package com.PersonalProject.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.PersonalProject.user.model.User;

@Repository
public interface UserMapper {
	public User selectUserByLoginId(String loginId);
	
	public User selectUserByNickname(String nickname);
	
	public int insertMembership(
			@Param("loginId") String loginId,
			@Param("nickname") String nickname,
			@Param("password") String password,
			@Param("email") String email);
	
	public User selectUserByLoginIdPassword(
			@Param("loginId") String loginId,
			@Param("password")String password);
	
	public User selectUserById(int id);
	
	public User selectUserByIntegerId(Integer userId);
	
	public void updateUser(
			@Param("profileImagePath")String profileImagePath,
			@Param("userId")int userId,
			@Param("nickname") String nickname);
}
