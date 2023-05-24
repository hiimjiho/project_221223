package com.PersonalProject.favorite.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PersonalProject.favorite.dao.FavoriteMapper;

@Service
public class FavoriteBO {
	@Autowired
	private FavoriteMapper favoriteMapper;
	
	public int addFavoriteByUserIdProductId(int userId, int productId) {
		return favoriteMapper.insertFavoriteByUserIdProductId(userId, productId);
	}
	
	public int deleteFavoriteByProductId(int productId, int userId) {
		return favoriteMapper.deleteFavoriteByProductId(productId, userId);
	}
}
