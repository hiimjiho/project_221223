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
import com.PersonalProject.post.model.Paging;
import com.PersonalProject.user.bo.UserBO;
import com.PersonalProject.user.model.User;

@RequestMapping("/favorite")
@Controller
public class FavoriteController {
	@Autowired
	private FavoriteBO favoriteBO;
	
	@Autowired
	private UserBO userBO;
	
	@GetMapping("/favorite_view")
	public String favoriteView(
			Model model,
			@RequestParam(value="page", required=false, defaultValue="1") int page,
			@RequestParam("userId") int userId) {
		
		User user = userBO.getUserById(userId);
		List<FavoriteView> favoriteView = favoriteBO.generateFavoriteByUserId(userId, page);
		Paging paging = favoriteBO.pagingParam(page, userId);
		
		model.addAttribute("paging", paging);
		model.addAttribute("user", user);
		model.addAttribute("favoriteView", favoriteView);
		model.addAttribute("view", "favorite/favoriteView");
		return "template/layout";
	}
}
