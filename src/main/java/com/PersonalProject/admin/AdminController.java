package com.PersonalProject.admin;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.PersonalProject.style.bo.StyleBO;
import com.PersonalProject.style.model.Style;
import com.PersonalProject.user.bo.UserBO;
import com.PersonalProject.user.model.User;

@RequestMapping("/admin")
@Controller
public class AdminController {
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private StyleBO styleBO;
	
	@GetMapping("/login_page_view")
	public String pageView() {
		
		return "admin/adminSignInView";
	}
	
	@GetMapping("/admin_main_view")
	public String adminMainView() {
		
		return "admin/adminMainView";
	}
	
	@GetMapping("/user_management_view")
	public String userManagementView(Model model,
			HttpSession session) {
		
		List<User> userList = userBO.getUserList();
		model.addAttribute("view", "admin/userManagementView");
		model.addAttribute("userList", userList);
		return "template/adminLayout";
	}
	
	@GetMapping("/style_management_view")
	public String styleManagementView(Model model,
			HttpSession session) {
		
		List<Style> styleList = styleBO.getStyleList();
		model.addAttribute("view", "admin/styleManagementView");
		model.addAttribute("styleList", styleList);
		return "template/adminLayout";
	}
}
