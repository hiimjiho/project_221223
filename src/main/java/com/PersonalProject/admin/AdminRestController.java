package com.PersonalProject.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PersonalProject.admin.bo.AdminBO;
import com.PersonalProject.admin.model.Admin;
import com.PersonalProject.product.bo.ProductBO;
import com.PersonalProject.user.bo.UserBO;
@RequestMapping("/admin")
@RestController
public class AdminRestController {
	
	@Autowired
	private AdminBO adminBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private ProductBO productBO;
	
	@PostMapping("/admin_sign_in")
	public Map<String, Object> adminSignIn(
			@RequestParam("adminLoginId") String adminLoginId,
			@RequestParam("adminPassword") String adminPassword,
			HttpSession session){
		
		Admin admin = adminBO.getAdminByAdminIdPassword(adminLoginId, adminPassword);
		
		Map<String, Object> result = new HashMap<>();
		if(admin != null) {
			result.put("result", "success");
			session.setAttribute("adminId", admin.getId());
			session.setAttribute("adminLoginId", admin.getAdminId());
			session.setAttribute("adminPassword", admin.getAdminPassword());
		} else {
			result.put("error", "다시 입력해주세요");
		}
		return result;
	}
	
	@RequestMapping("/sign_out")
	public String signOut(HttpSession session) {
		// 세션에 있는 모든 것을 비운다.
		session.removeAttribute("adminId");
		session.removeAttribute("adminLoginId");
		session.removeAttribute("adminPassword");
		
		return "redirect:/admin/login_page_view";
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
		
		Map<String, Object> result = new HashMap<>();
		adminBO.deleteStyleByStyleId(styleId);
		result.put("code", 1);
		result.put("result", "성공");
		return result;
	}
	
	@PutMapping("/update_product")
	public Map<String, Object> updateProduct(
			HttpSession session,
			@RequestParam("productId")int productId,
			@RequestParam("brand")String brand,
			@RequestParam("name")String name,
			@RequestParam("productImagePath")String productImagePath){
		
		Map<String, Object> result = new HashMap<>();
		productBO.updateProduct(productId, brand, name, productImagePath);
		result.put("code", 1);
		result.put("result", "성공");
		return result;
	}
	
	@PostMapping("/product_create")
	public Map<String, Object> productCreate(
			HttpSession session,
			@RequestParam("name")String name,
			@RequestParam("brand")String brand,
			@RequestParam("productImagePath")String productImagePath){
		
		Map<String, Object> result = new HashMap<>();
		productBO.addProduct(name, brand, productImagePath);
		result.put("code", 1);
		result.put("result", "성공");
		return result;
	}
	
	@DeleteMapping("/post_delete")
	public Map<String, Object> deletePost(
			HttpSession session,
			@RequestParam("postId") int postId){
		
		Map<String, Object> result = new HashMap<>();
		adminBO.deletePostByPostId(postId);
		result.put("code", 1);
		result.put("result", "성공");
		return result;
	}
	
	@DeleteMapping("/product_delete")
	public Map<String, Object> deleteProduct(
			@RequestParam("productId") int productId){
		
		Map<String, Object> result = new HashMap<>();
		adminBO.deleteProductByProductId(productId);
		result.put("code", 1);
		result.put("result", "성공");
		return result;
	}
}
