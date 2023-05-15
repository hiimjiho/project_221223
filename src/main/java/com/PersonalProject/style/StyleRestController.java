package com.PersonalProject.style;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.PersonalProject.style.bo.StyleBO;
@RequestMapping("/style")
@RestController
public class StyleRestController {
	@Autowired
	private StyleBO styleBO;
	
	@PostMapping("/create")
	public Map<String, Object> styleCreate(
			@RequestParam("productId") int productId,
			@RequestParam("content") String content,
			@RequestParam(value = "file", required=false) MultipartFile file,
			HttpSession session){
		
		Integer userId = (Integer)session.getAttribute("userId");
		Map<String, Object> result = new HashMap<>();
		if(userId == null) {
			result.put("code", 500);
			result.put("result", "error");
			result.put("errorMessage", "error");
			return result;
		}
		styleBO.addStyle(productId, content, userId, file);
		result.put("code", 1);
		result.put("result", "성공");
		return result;
		
	}
	
}
