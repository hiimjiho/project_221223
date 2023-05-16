package com.PersonalProject.styleComment.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StyleCommentMapper {
	public int insertStyleComment(
			@Param("userId") int userId,
			@Param("styleId")int styleId,
			@Param("content")String content);
	
	public int deleteStyleComment(
			@Param("styleId") int styleId,
			@Param("styleCommentId")int styleCommentId);
}
