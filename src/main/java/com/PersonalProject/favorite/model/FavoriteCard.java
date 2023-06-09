package com.PersonalProject.favorite.model;

import com.PersonalProject.product.model.Product;
import com.PersonalProject.user.model.User;

public class FavoriteCard {

	private Product product;
	private User user;
	private Favorite favorite;
	
	public Favorite getFavorite() {
		return favorite;
	}

	public void setFavorite(Favorite favorite) {
		this.favorite = favorite;
	}

	// 관심 상품 등록했는지 여부
	private boolean hetherFavorite;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isHetherFavorite() {
		return hetherFavorite;
	}

	public void setHetherFavorite(boolean hetherFavorite) {
		this.hetherFavorite = hetherFavorite;
	}
	
	
}
