package com.PersonalProject.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdminController {
	@GetMapping("/login_page_view")
	public String pageView() {
		
		return "admin/adminSignInView";
	}
	
	@GetMapping("/admin_main_view")
	public String adminMainView() {
		
		return "admin/adminMainView";
	}
}
