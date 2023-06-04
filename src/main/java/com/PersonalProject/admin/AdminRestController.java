package com.PersonalProject.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PersonalProject.admin.bo.AdminBO;
import com.PersonalProject.admin.model.Admin;
import com.PersonalProject.user.bo.UserBO;
@RequestMapping("/admin")
@RestController
public class AdminRestController {
	
	@Autowired
	private AdminBO adminBO;
	
	@Autowired
	private UserBO userBO;
	
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
	
	@DeleteMapping("/user_delete")
	public Map<String, Object> userDelete(
			@RequestParam("userId") int userId,
			HttpSession session){
		
		Map<String, Object> result = new HashMap<>();
		int rowCount = userBO.deleteUserByUserId(userId);
		
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("result", "성공");
		}else {
			result.put("code", 500);
			result.put("result", "실패");
		}
		return result;
	}
	
	@DeleteMapping("/delete_style")
	public Map<String, Object> styleDelete(
			HttpSession session,
			@RequestParam("styleId") int styleId){
		
		Integer adminId = (Integer)session.getAttribute("adminId");
		Map<String, Object> result = new HashMap<>();
		if(adminId == null) {
			result.put("code", 500);
			result.put("result", "error");
			result.put("result", "errorMessage");
		}
		adminBO.deleteStyleByStyleId(styleId);
		result.put("code", 1);
		result.put("result", "성공");
		return result;
	}
}
