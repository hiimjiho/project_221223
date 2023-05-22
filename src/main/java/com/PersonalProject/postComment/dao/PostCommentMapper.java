package com.PersonalProject.postComment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.PersonalProject.postComment.model.PostComment;

@Repository
public interface PostCommentMapper {
	
	public int addPostCommentByContentPostId(
			@Param("postId")int postId,
			@Param("userId") int userId,
			@Param("content")String content);
	
	public List<PostComment> selectPostCommentListByPostId(int postId);
	
	public int deletePostCommentByCommentId(int postCommentId);
}
