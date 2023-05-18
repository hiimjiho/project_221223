package com.PersonalProject.style;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.PersonalProject.product.bo.ProductBO;
import com.PersonalProject.product.model.Product;
import com.PersonalProject.style.bo.StyleBO;
import com.PersonalProject.style.model.StyleCard;

@RequestMapping("/style")
@Controller
public class StyleController {
	@Autowired
	private ProductBO productBO;
	@Autowired
	private StyleBO styleBO;
//	@Autowired
//	private StyleCommentBO styleCommentBO;
	
	
	@GetMapping("/detail_view")
	public String detailView(Model model,
			@RequestParam("productId") int productId,
			HttpSession session) {
		
		Product product = productBO.getProductByProductId(productId);
//		List<Style> styleList = styleBO.getStyleByProductId(productId);
//		List<StyleComment> commentList = styleCommentBO.getStyleComment();
		
		Integer userId = (Integer)session.getAttribute("userId");
		List<StyleCard> styleCardList = styleBO.generateStyleCardByProductId(productId, userId);
		
		model.addAttribute("styleCardList", styleCardList);
		//model.addAttribute("styleList", styleList);
		model.addAttribute("product", product);
		//model.addAttribute("commentList", commentList);
		model.addAttribute("view", "style/styleDetailView");
		return "template/layout";
	}
}
