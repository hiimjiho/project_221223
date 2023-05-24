package com.PersonalProject.favorite;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.PersonalProject.favorite.bo.FavoriteBO;
import com.PersonalProject.favorite.model.FavoriteView;

@RequestMapping("/favorite")
@Controller
public class FavoriteController {
	@Autowired
	private FavoriteBO favoriteBO;
	
	@GetMapping("/favorite_view")
	public String favoriteView(
			Model model,
			@RequestParam("userId") int userId) {
		
		List<FavoriteView> favoriteView = favoriteBO.generateFavoriteByUserId(userId);
		
		model.addAttribute("favoriteView", favoriteView);
		model.addAttribute("view", "favorite/favoriteView");
		return "template/layout";
	}
}
