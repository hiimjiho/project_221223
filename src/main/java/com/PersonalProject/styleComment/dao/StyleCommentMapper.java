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
}
