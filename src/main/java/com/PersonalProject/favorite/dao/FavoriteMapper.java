package com.PersonalProject.favorite.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.PersonalProject.favorite.model.Favorite;

@Repository
public interface FavoriteMapper {
	public int insertFavoriteByUserIdProductId(
			@Param("userId")int userId,
			@Param("productId")int productId);
	
	public int deleteFavoriteByProductId(
			@Param("productId")int productId,
			@Param("userId") int userId);
	
	public List<Favorite> selectFavoriteListByUserId(int userId);
}
