package com.PersonalProject.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.PersonalProject.post.model.Post;

@Repository
public interface PostMapper {
	public int insertPost(
			@Param("userId")int userId,
			@Param("loginId")String loginId,
			@Param("subject")String subject,
			@Param("content")String content,
			@Param("imagePath")String imagePath);
	
	public List<Post> selectPostList();
	
	public Post selectPost(int postId);
}
