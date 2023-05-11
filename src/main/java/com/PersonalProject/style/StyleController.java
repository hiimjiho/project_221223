package com.PersonalProject.style;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.PersonalProject.style.bo.StyleBO;
import com.PersonalProject.style.model.Style;

@RequestMapping("/style")
@Controller
public class StyleController {
	@Autowired
	private StyleBO styleBO;
	
	@GetMapping("/detail_view")
	public String detailView(Model model,
			@RequestParam("productId") int productId) {
		
		Style style = styleBO.getStyleByProductId(productId);
		
		model.addAttribute("style", style);
		model.addAttribute("view", "style/styleDetailView");
		return "template/rayout";
	}
}
