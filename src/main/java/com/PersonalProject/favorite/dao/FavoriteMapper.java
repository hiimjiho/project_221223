package com.PersonalProject.favorite.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteMapper {
	public int insertFavoriteByUserIdProductId(
			@Param("userId")int userId,
			@Param("productId")int productId);
	
	public int deleteFavoriteByProductId(
			@Param("productId")int productId,
			@Param("userId") int userId);
}
