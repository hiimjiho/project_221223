package com.PersonalProject.admin;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.PersonalProject.post.bo.PostBO;
import com.PersonalProject.post.model.Post;
import com.PersonalProject.product.bo.ProductBO;
import com.PersonalProject.product.model.Product;
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
	
	@Autowired
	private ProductBO productBO;
	
	@Autowired
	private PostBO postBO;
	
	@GetMapping("/login_page_view")
	public String pageView() {
		
		return "admin/adminSignInView";
	}
	
	@GetMapping("/admin_main_view")
	public String adminMainView(Model model) {
		
		model.addAttribute("view", "admin/adminMainView");
		return "template/adminLayout";
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
	
	@GetMapping("/product_management_view")
	public String productManagementView(Model model) {
		
		List<Product> productList = productBO.adminGetProductList();
		model.addAttribute("productList", productList);
		model.addAttribute("view", "admin/ProductManagementView");
		return "template/adminLayout";
	}
	
	@GetMapping("/product_update_view")
	public String productUpdateView(Model model,
			@RequestParam("productId") int productId) {
		
		Product product = productBO.adminGetProductByProductId(productId);
		model.addAttribute("product", product);
		model.addAttribute("view", "admin/productUpdateView");
		return "template/adminLayout";
	}
	
	@GetMapping("/product_create_view")
	public String productCreateView(Model model) {
		
		model.addAttribute("view", "admin/productCreateView");
		return "template/adminLayout";
	}
	
	@GetMapping("/post_management_view")
	public String postManagementView(Model model) {
		
		List<Post> postList = postBO.getPostList();
		model.addAttribute("postList", postList);
		model.addAttribute("view", "admin/postManagementView");
		return "template/adminLayout";
	}
}
