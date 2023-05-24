package com.PersonalProject.favorite.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PersonalProject.favorite.dao.FavoriteMapper;
import com.PersonalProject.favorite.model.Favorite;
import com.PersonalProject.favorite.model.FavoriteView;
import com.PersonalProject.product.bo.ProductBO;
import com.PersonalProject.product.model.Product;
import com.PersonalProject.user.bo.UserBO;
import com.PersonalProject.user.model.User;

@Service
public class FavoriteBO {
	@Autowired
	private FavoriteMapper favoriteMapper;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private ProductBO productBO;
	
	public int addFavoriteByUserIdProductId(int userId, int productId) {
		return favoriteMapper.insertFavoriteByUserIdProductId(userId, productId);
	}
	
	public int deleteFavoriteByProductId(int productId, int userId) {
		return favoriteMapper.deleteFavoriteByProductId(productId, userId);
	}
	
	public List<FavoriteView> generateFavoriteByUserId(int userId){
		List<FavoriteView> favoriteViewList = new ArrayList<>();
		
		List<Favorite> favoriteList = favoriteMapper.selectFavoriteListByUserId(userId);
		
		for(Favorite favorite : favoriteList) {
			FavoriteView favoriteView = new FavoriteView();
			
			favoriteView.setFavorite(favorite);
			
			User user = userBO.getUserById(favorite.getUserId());
			
			favoriteView.setUser(user);
			
			Product product = productBO.getProductByProductId(favorite.getProductId());
			
			favoriteView.setProduct(product);
			
			favoriteViewList.add(favoriteView);
			
		}
		return favoriteViewList;
	}
}
