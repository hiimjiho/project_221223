package com.PersonalProject.postComment.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCommentMapper {
	
	public int addPostCommentByContentPostId(
			@Param("postId")int postId,
			@Param("content")String content);
}
