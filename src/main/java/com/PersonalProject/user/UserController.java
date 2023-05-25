package com.PersonalProject.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.PersonalProject.user.bo.UserBO;
@RequestMapping("/user")
@Controller
public class UserController {
	@Autowired
	private UserBO userBO;
	
	@GetMapping("/sign_up_view")
	public String signUpView(Model model) {
		model.addAttribute("view", "user/signUp");
		return "template/layout";
	}
	
	@GetMapping("/sign_in_view")
	public String signInView(Model model) {
		model.addAttribute("view", "user/signIn");
		
		return "template/layout";
	}
	
	@RequestMapping("/sign_out")
	public String signOut(HttpSession session) {
		// 세션에 있는 모든 것을 비운다.
		session.removeAttribute("userId");
		session.removeAttribute("userName");
		session.removeAttribute("userLoginId");
		
		return "redirect:/user/sign_in_view";
	}
	
	@PutMapping("/profile_update")
	public String profileUpdate(
			Model model,
			HttpSession session,
			@RequestParam(value="file", required=false) MultipartFile file,
			@RequestParam("nickname") String nickname) {
		
		int userId = (int)session.getAttribute("userId");
		String userLoginId = (String)session.getAttribute("userLoginId");
		
		userBO.updateUser(file, userId, userLoginId, nickname);
		model.addAttribute("view", "profile/profileUpdate");
		return "template/layout";
	}


}
