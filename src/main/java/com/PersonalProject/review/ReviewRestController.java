package com.PersonalProject.review;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PersonalProject.review.bo.ReviewBO;

@RequestMapping("/review")
@RestController
public class ReviewRestController {
	@Autowired
	private ReviewBO reviewBO;

	@PostMapping("/create")
	public Map<String, Object> reviewCreate(
			HttpSession session,
			@RequestParam("productId") int productId,
			@RequestParam("content") String content){
		
		Integer userId = (Integer)session.getAttribute("userId");
		Map<String, Object> result = new HashMap<>();
		
		if(userId == null) {
			result.put("code", 500);
			result.put("result", "error");
			result.put("errorMessage", "error");
			return result;
		}
		reviewBO.addReview(userId, productId, content);
		result.put("code", 1);
		result.put("result", "성공");
		return result;
		
		
	}
}
