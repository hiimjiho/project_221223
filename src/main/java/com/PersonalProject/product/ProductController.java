package com.PersonalProject.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.PersonalProject.product.bo.ProductBO;
import com.PersonalProject.product.model.Product;
@RequestMapping("/product")
@Controller
public class ProductController {
	@Autowired
	private ProductBO productBO;
	
	@GetMapping("/main_view")
	public String mainView(Model model) {
		List<Product> productList = productBO.getProductList();
		
		model.addAttribute("productList", productList);
		model.addAttribute("view", "product/mainView");
		return "template/layout";
	}
}
