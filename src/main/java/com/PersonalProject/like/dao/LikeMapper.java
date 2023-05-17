package com.PersonalProject.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.PersonalProject.like.model.Like;

@Repository
public interface LikeMapper {
	
	public int likeCountByUserIdStyleId(
			@Param("styleId") int styleId,
			@Param("userId") Integer userId);
	
	public void likeDeleteByUserIdStyleId(
			@Param("styleId") int styleId,
			@Param("userId") int userId);
	
	public void likeInsertByUserIdStyleId(
			@Param("styleId") int styleId,
			@Param("userId") int userId);
	
	public Like selectLike(
			@Param("styleId") int styleId,
			@Param("userId") int userId);
}
