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
	
	// 페이징
	public List<Favorite> selectFavoriteListByUserId(
			@Param("userId")int userId,
			@Param("pageStart") int pageStart,
			@Param("pageLimit") int pageLimit);
	
	public int countFavoriteByUserId(int userId);
	
	public Favorite selectFavoriteByProductIdUserId(
			@Param("productId") int productId,
			@Param("userId") int userId);
	
	public Favorite selectFavoriteByProductId(int productId);
	
	// 토글
	public void deleteFavoriteByUserIdProductId(
			@Param("userId")int userId,
			@Param("productId")int productId);
	
	// 토글
	public void insertToggleFavoriteByUserIdProductId(
			@Param("userId")int userId,
			@Param("productId")int productId); 
	
	public int selectCountFavoriteByUserIdProductId(
			@Param("userId")int userId,
			@Param("productId")int productId);
	
	public void deleteFavoriteByUserId(int userId);
	
	// 관리자 신발 삭제할때 사용
	public void adminDeleteFavoriteByProductId(int productId);
	
}
