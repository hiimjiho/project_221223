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
	
	public Post selectPostByPostIdUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	public void updatePostByPostId(
			@Param("postId") int postId,
			@Param("subject") String subject,
			@Param("content") String content,
			@Param("imagePath") String imagePath);
	
	public int deletePostByPostIdUserId(
			@Param("postId")int postId,
			@Param("userId")int userId);
	
	// 코멘트 삭제를 위한 메서드
	public Post selectPostByPostId(int postId);
	
	// 프로필 화면 구성할 때 쓸 포스트 리스트
	public List<Post> selectPostListByUserId(int userId);
	
	public int countPost();
	
	public List<Post> pagingPostList(
			@Param("pageStart") int pageStart,
			@Param("pageLimit") int pageLimit);
	// 어드민 페이지에서 사용할 포스트 삭제
	public int deletePostByPostId(int postId);
	
	// 어드민 페이지 유저 탈퇴 기능에 사용할 포스트 삭제
	public void deletePostByUserId(int userId);
	
}
