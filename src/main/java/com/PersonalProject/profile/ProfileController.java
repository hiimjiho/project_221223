package com.PersonalProject.profile;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("/profile")
@Controller
public class ProfileController {
	@GetMapping("/profile_view")
	public String profileView(Model model) {
		
		model.addAttribute("view", "profile/userProfile");
		return "template/layout";
	}
}
