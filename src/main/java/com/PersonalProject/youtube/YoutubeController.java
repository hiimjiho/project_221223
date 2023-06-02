package com.PersonalProject.youtube;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("/youtube")
@Controller
public class YoutubeController {
	
	@GetMapping("/search")
	public String youtubeSearch(Model model) {
		
		model.addAttribute("view", "youtube/youtubeSearchView");
		return "template/layout";
	}
}
