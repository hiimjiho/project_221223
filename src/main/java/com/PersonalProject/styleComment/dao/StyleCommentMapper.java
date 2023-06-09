package com.PersonalProject.styleComment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.PersonalProject.styleComment.model.StyleComment;

@Repository
public interface StyleCommentMapper {
	public int insertStyleComment(
			@Param("userId") int userId,
			@Param("styleId")int styleId,
			@Param("content")String content);
	
	public int deleteStyleComment(int styleCommentId);
	
	public List<StyleComment> selectStyleComment();
	
	public List<StyleComment> selectStyleCommentListByStyleId(int styleId);
	
	public void deleteCommentByStyleId(int StyleId);
	
	// 페이징
	public int countStyleCommentByStyleId(int styleId);
	
	// 페이징
	public List<StyleComment> selectStyleCommentByStyleId(
			@Param("styleId")int styleId,
			@Param("pageStart") int pageStart,
			@Param("pageLimit") int pageLimit);
	
	// 어드민 페이지 유저 탈퇴 기능에 사용할 스타일 코멘트 삭제
	public void deleteStyleCommentByUserId(int userId);
}
