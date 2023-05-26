package com.PersonalProject.favorite.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PersonalProject.favorite.dao.FavoriteMapper;
import com.PersonalProject.favorite.model.Favorite;
import com.PersonalProject.favorite.model.FavoriteCard;
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
	
	public List<FavoriteView> generateFavoriteByUserId(Integer userId){
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
	
	public void favoriteToggle(int userId, int productId) {
		int favoriteCount = favoriteMapper.selectCountFavoriteByUserIdProductId(userId, productId);
		
		if(favoriteCount > 0) {
			favoriteMapper.deleteFavoriteByUserIdProductId(userId, productId);
		}else {
			favoriteMapper.insertToggleFavoriteByUserIdProductId(userId, productId);
		}
	}
	
	public FavoriteCard generateFavByUserId(int productId) {
		FavoriteCard favoriteView = new FavoriteCard();
		
		Favorite favorite = favoriteMapper.selectFavoriteByProductId(productId);
		
		favoriteView.setFavorite(favorite);
		
		User user = userBO.getUserById(favorite.getUserId());
		
		favoriteView.setUser(user);
		
		Product product = productBO.getProductByProductId(productId);
		
		favoriteView.setProduct(product);
		
		if(user == null) {
			favoriteView.setHetherFavorite(false);
		}else {
			Favorite fav = favoriteMapper.selectFavoriteByProductIdUserId(product.getId(), user.getId());
			if(fav == null) {
				favoriteView.setHetherFavorite(false);
			}else {
				favoriteView.setHetherFavorite(true);
			}
		}
		return favoriteView;
	}
}
