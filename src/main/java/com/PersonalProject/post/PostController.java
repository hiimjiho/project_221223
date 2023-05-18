package com.PersonalProject.post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("/post")
@Controller
public class PostController {
	
	@GetMapping("/list_view")
	public String postListView(Model model) {
		
		model.addAttribute("view", "post/postListView");
		return "template/layout";
	}
}
