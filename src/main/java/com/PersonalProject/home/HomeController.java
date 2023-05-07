package com.PersonalProject.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/home")

@Controller
public class HomeController {
	@GetMapping("/main_view")
	public String mainView(Model model) {
		model.addAttribute("view", "home/mainView");
		return "template/layout";
	}

}
