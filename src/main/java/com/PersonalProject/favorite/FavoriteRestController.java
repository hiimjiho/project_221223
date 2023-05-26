package com.PersonalProject.favorite;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PersonalProject.favorite.bo.FavoriteBO;

@RequestMapping("/favorite")
@RestController
public class FavoriteRestController {
	@Autowired
	private FavoriteBO favoriteBO;
	
	@PostMapping("/favorite_insert")
	public Map<String, Object> favoriteInsert(
			HttpSession session,
			@RequestParam("productId") int productId) {
		
		int userId = (int)session.getAttribute("userId");
		Map<String, Object> result = new HashMap<>();
		favoriteBO.addFavoriteByUserIdProductId(userId, productId);
		result.put("code", 1);
		result.put("result", "성공");
		return result;
	}
	
	@DeleteMapping("/favorite_delete")
	public Map<String, Object> favoriteDelete(
			HttpSession session,
			@RequestParam("productId") int productId){
		
		int userId = (int)session.getAttribute("userId");
		favoriteBO.deleteFavoriteByProductId(productId, userId);
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("result", "성공");
		return result;
	}
	
	@RequestMapping("/favorite/{productId}")
	public Map<String, Object> favToggle(
			@PathVariable("productId") int productId,
			HttpSession session){
		
		Integer userId = (Integer)session.getAttribute("userId");
		Map<String, Object> result = new HashMap<>();
		favoriteBO.favoriteToggle(userId, productId);
		if(userId == null) {
			result.put("code", 500);
			result.put("result", "error");
			result.put("result", "errorMessage");
			return result;
		}
		result.put("code", 1);
		result.put("result", "성공");	
		return result;
	}
}
