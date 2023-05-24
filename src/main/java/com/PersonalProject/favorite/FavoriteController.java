package com.PersonalProject.favorite;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/favorite")
@Controller
public class FavoriteController {
	
	@GetMapping("/favorite_view")
	public String favoriteView(
			Model model,
			HttpSession session) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		model.addAttribute("view", "favorite/favoriteView");
		return "template/layout";
	}
}
