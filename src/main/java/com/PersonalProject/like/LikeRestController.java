package com.PersonalProject.like;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PersonalProject.like.bo.LikeBO;

@RestController
public class LikeRestController {
	@Autowired
	private LikeBO likeBO;
	
	@RequestMapping("/like/{styleId}")
	public Map<String, Object> like(
			@PathVariable("styleId") int styleId,
			HttpSession session){
		
		Integer userId = (Integer)session.getAttribute("userId");
		Map<String, Object> result = new HashMap<>();
		likeBO.likeToggole(userId, styleId);
		if(userId == null) {
			result.put("code", 500);
			result.put("result", "error");
			result.put("result", "errorMessage");
			return result;
		}
		
		result.put("code", 1);
		result.put("result", "성공");
		return result;
	}
}
