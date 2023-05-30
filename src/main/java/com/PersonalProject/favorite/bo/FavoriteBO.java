package com.PersonalProject.favorite.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PersonalProject.favorite.dao.FavoriteMapper;
import com.PersonalProject.favorite.model.Favorite;
import com.PersonalProject.favorite.model.FavoriteCard;
import com.PersonalProject.favorite.model.FavoriteView;
import com.PersonalProject.post.model.Paging;
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
	
	private static final int PAGE_LIMIT = 5; // 한 페이지당 보여줄 글 갯수
	
	private static final int BLOCK_LIMIT = 5; // 
	
	public int addFavoriteByUserIdProductId(int userId, int productId) {
		return favoriteMapper.insertFavoriteByUserIdProductId(userId, productId);
	}
	
	public int deleteFavoriteByProductId(int productId, int userId) {
		return favoriteMapper.deleteFavoriteByProductId(productId, userId);
	}
	
	public List<FavoriteView> generateFavoriteByUserId(Integer userId, int page){
		
		int pageStart = (page -1) * PAGE_LIMIT;
		Map<String, Integer> pagingParams = new HashMap<>();
		pagingParams.put("start", pageStart);
		pagingParams.put("limit", PAGE_LIMIT);
		
		List<FavoriteView> favoriteViewList = new ArrayList<>();
		
		List<Favorite> favoriteList = favoriteMapper.selectFavoriteListByUserId(userId, pageStart, PAGE_LIMIT);
		
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
	
	public Paging pagingParam(int page, int userId) {
		 // 전체 글 갯수 조회
       int boardCount = favoriteMapper.countFavoriteByUserId(userId);
       
       // 전체 페이지 갯수 계산(10/3=3.33333 => 4)
       int maxPage = (int) (Math.ceil((double) boardCount / PAGE_LIMIT));
       
       // 시작 페이지 값 계산(1, 4, 7, 10, ~~~~)
       int startPage = (((int)(Math.ceil((double) page / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1;
       
       // 끝 페이지 값 계산(3, 6, 9, 12, ~~~~)
       int endPage = startPage + BLOCK_LIMIT - 1;
       
       if (endPage > maxPage) {
           endPage = maxPage;
       }
       Paging paging = new Paging();
       
       paging.setPage(page);
       paging.setMaxPage(maxPage);
       paging.setStartPage(startPage);
       paging.setEndPage(endPage);
       return paging;
	}
	
	public void favoriteToggle(int userId, int productId) {
		int favoriteCount = favoriteMapper.selectCountFavoriteByUserIdProductId(userId, productId);
		
		if(favoriteCount > 0) {
			favoriteMapper.deleteFavoriteByUserIdProductId(userId, productId);
		}else {
			favoriteMapper.insertToggleFavoriteByUserIdProductId(userId, productId);
		}
	}
	
	public FavoriteCard generateFavByUserId(int productId, Integer userId) {
		
		FavoriteCard favoriteView = new FavoriteCard();
		
		Product product = productBO.getProductByProductId(productId);
		favoriteView.setProduct(product);
		
		User user = userBO.getUserByIntegerId(userId);
		favoriteView.setUser(user);
		
		if(userId == null) {
			favoriteView.setHetherFavorite(false);
		}else {
			Favorite favorite = favoriteMapper.selectFavoriteByProductIdUserId(productId, userId);
			if(favorite == null) {
				favoriteView.setHetherFavorite(false);
			}else {
				favoriteView.setHetherFavorite(true);
			}
		}
		return favoriteView;
	}
}
