package com.PersonalProject.product;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.PersonalProject.favorite.bo.FavoriteBO;
import com.PersonalProject.favorite.model.FavoriteCard;
import com.PersonalProject.post.model.Paging;
import com.PersonalProject.product.bo.ProductBO;
import com.PersonalProject.product.model.Product;
import com.PersonalProject.review.bo.ReviewBO;
import com.PersonalProject.review.model.ReviewCard;
import com.PersonalProject.style.bo.StyleBO;
import com.PersonalProject.style.model.Style;
@RequestMapping("/product")
@Controller
public class ProductController {
	@Autowired
	private ProductBO productBO;
	@Autowired
	private StyleBO styleBO;
	@Autowired
	private ReviewBO reviewBO;
	@Autowired
	private FavoriteBO favoriteBO;
	
	@GetMapping("/main_view")
	public String mainView(Model model,
			@RequestParam(value="page", required=false, defaultValue="1") int page) {
		List<Product> productList = productBO.getProductList(page);
		Paging paging = productBO.pagingParam(page);
		
		model.addAttribute("paging", paging);
		model.addAttribute("productList", productList);
		model.addAttribute("view", "product/mainView");
		return "template/layout";
	}
	
	@GetMapping("/detail_view")
	public String datailView(
			Model model,
			@RequestParam("productId") int productId,
			@RequestParam(value="page", required=false, defaultValue="1") int page,
			HttpSession session) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		Product product = productBO.getProductByProductId(productId);
		List<Style> styleList = styleBO.getStyleByProductIdLimit5(productId);
		Paging paging = reviewBO.pagingParamByProductId(page, productId);
		
		List<ReviewCard> reviewList = reviewBO.generateReview(productId, userId, page);
		FavoriteCard favoriteCard = favoriteBO.generateFavByUserId(productId, userId);
		
		model.addAttribute("paging", paging);
		model.addAttribute("favoriteCard", favoriteCard);
		model.addAttribute("reviewList", reviewList);
		model.addAttribute("styleList", styleList);
		model.addAttribute("product", product);
		model.addAttribute("view", "product/detailView");
		return "template/layout";
	}
	
	@GetMapping("/brand_list_view")
	public String brandListView(Model model) {
		model.addAttribute("view", "product/brandList");
		return "template/layout";
	}
	
	@GetMapping("/brand_detail_list_view")
	public String brandDetailListView(
			Model model,
			@RequestParam(value="page", required=false, defaultValue="1") int page,
			@RequestParam("brand") String brand) {
		
		List<Product> productBrandList = productBO.getProductListByBrand(brand, page);
		Paging paging = productBO.pagingParamByBrand(page, brand);
		
		model.addAttribute("productBrandList", productBrandList);
		model.addAttribute("paging", paging);
		model.addAttribute("view", "product/brandDetailView");
		return "template/layout";
	}
	
}
