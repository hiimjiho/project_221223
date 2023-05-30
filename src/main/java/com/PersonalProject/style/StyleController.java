package com.PersonalProject.style;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.PersonalProject.post.model.Paging;
import com.PersonalProject.product.bo.ProductBO;
import com.PersonalProject.product.model.Product;
import com.PersonalProject.style.bo.StyleBO;
import com.PersonalProject.style.model.StyleCard;
import com.PersonalProject.styleComment.bo.StyleCommentBO;

@RequestMapping("/style")
@Controller
public class StyleController {
	@Autowired
	private ProductBO productBO;
	@Autowired
	private StyleBO styleBO;
	@Autowired
	private StyleCommentBO styleCommentBO;
	
	
	@GetMapping("/detail_view")
	public String detailView(Model model,
			@RequestParam("productId") int productId,
			@RequestParam(value="page", required=false, defaultValue="1") int page,
			HttpSession session) {
		
		Product product = productBO.getProductByProductId(productId);
//		List<Style> styleList = styleBO.getStyleByProductId(productId);
//		List<StyleComment> commentList = styleCommentBO.getStyleComment();
		
		Integer userId = (Integer)session.getAttribute("userId");
		List<StyleCard> styleCardList = styleBO.generateStyleCardByProductId(productId, userId, page);
		Paging paging = styleBO.pagingParamByProductId(page, productId);
		
		model.addAttribute("paging", paging);
		model.addAttribute("styleCardList", styleCardList);
		//model.addAttribute("styleList", styleList);
		model.addAttribute("product", product);
		//model.addAttribute("commentList", commentList);
		model.addAttribute("view", "style/styleDetailView");
		return "template/layout";
	}
	
	@GetMapping("/style_detail_view")
	public String styleDetailView(
			Model model,
			HttpSession session,
			@RequestParam("styleId") int styleId,
			@RequestParam(value="page", required=false, defaultValue="1") int page) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		StyleCard styleCard = styleBO.generateStyle(styleId, userId, page);
		Paging paging = styleCommentBO.pagingParam(page, styleId);
		
		model.addAttribute("paging", paging);
		model.addAttribute("styleCard", styleCard);
		model.addAttribute("view", "style/styleSingleView");
		return "template/layout";
	}
	
	@GetMapping("/style_list_view")
	public String styleListView(Model model) {
		
		return "template/layout";
	}
}
