package com.PersonalProject.post.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostMapper {
	public int insertPost(
			@Param("userId")int userId,
			@Param("loginId")String loginId,
			@Param("subject")String subject,
			@Param("content")String content,
			@Param("imagePath")String imagePath);
}
