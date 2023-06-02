package com.PersonalProject.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PersonalProject.admin.bo.AdminBO;
import com.PersonalProject.admin.model.Admin;
@RequestMapping("/admin")
@RestController
public class AdminRestController {
	
	@Autowired
	private AdminBO adminBO;
	
	@PostMapping("/admin_sign_in")
	public Map<String, Object> adminSignIn(
			@RequestParam("adminLoginId") String adminLoginId,
			@RequestParam("adminPassword") String adminPassword,
			HttpSession session){
		
		Admin admin = adminBO.getAdminByAdminIdPassword(adminLoginId, adminPassword);
		
		Map<String, Object> result = new HashMap<>();
		if(admin != null) {
			result.put("result", "success");
			session.setAttribute("id", admin.getId());
			session.setAttribute("adminLoginId", admin.getAdminId());
			session.setAttribute("adminPassword", admin.getAdminPassword());
		} else {
			result.put("error", "다시 입력해주세요");
		}
		return result;
	}
}
