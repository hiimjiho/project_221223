package com.PersonalProject.profile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.PersonalProject.post.bo.PostBO;
import com.PersonalProject.post.model.Post;
import com.PersonalProject.style.bo.StyleBO;
import com.PersonalProject.style.model.Style;
import com.PersonalProject.user.bo.UserBO;
import com.PersonalProject.user.model.User;
@RequestMapping("/profile")
@Controller
public class ProfileController {
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private StyleBO styleBO;
	
	@Autowired
	private PostBO postBO;
	
	@GetMapping("/profile_view")
	public String profileView(Model model,
			@RequestParam("userId") int userId) {
		
		User user = userBO.getUserById(userId);
		
		model.addAttribute("user", user);
		model.addAttribute("view", "profile/userProfile");
		return "template/layout";
	}
	
	@GetMapping("/profile_style_view")
	public String profileStyleView(
			@RequestParam(value="userId", required=false) Integer userId,
			Model model) {
		
		User user = userBO.getUserByIntegerId(userId);
		List<Style> styleList = styleBO.getStyleListByUserId(userId);
		
		model.addAttribute("user", user);
		model.addAttribute("styleList", styleList);
		model.addAttribute("view", "profile/userProfileStyle");
		return "template/layout";
	}
	
	@GetMapping("/profile_post_view")
	public String profilePostView(
			@RequestParam(value="userId", required=false) Integer userId,
			Model model) {
		
		User user = userBO.getUserByIntegerId(userId);
		List<Post> postList = postBO.getPostListByUserId(userId);
		
		model.addAttribute("user", user);
		model.addAttribute("postList", postList);
		model.addAttribute("view", "profile/userProfilePost");
		return "template/layout";
	}
}
