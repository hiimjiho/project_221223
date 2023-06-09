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
	
	public List<PostComment> selectPostCommentByPostIdUserIdPostCommentId(
			@Param("postId")int postId,
			@Param("userId")int userId,
			@Param("postCommentId") int postCommentId);
	
	public void deletePostCommentByPostId(int postId);
	
	// 어드민 페이지 유저 삭제 기능에 사용할 포스트 코멘트 삭제
	public void deleteCommentByUserId(int userId);
}
