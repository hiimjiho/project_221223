package com.PersonalProject.favorite.model;

import java.util.List;

import com.PersonalProject.product.model.Product;
import com.PersonalProject.user.model.User;

public class FavoriteView {
	private User user;
	private Favorite favorite;
	private List<Favorite> favoriteList;
	private Product product;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Favorite getFavorite() {
		return favorite;
	}
	public void setFavorite(Favorite favorite) {
		this.favorite = favorite;
	}
	public List<Favorite> getFavoriteList() {
		return favoriteList;
	}
	public void setFavoriteList(List<Favorite> favoriteList) {
		this.favoriteList = favoriteList;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
