package com.PersonalProject.postComment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/post_comment")
@RestController
public class PostRestController {
	@PostMapping("/create")
	public Map<String, Object> postCommentCreate(HttpSession session,
			@RequestParam("postId") int postId,
			@RequestParam("content") String content){
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		Map<String, Object> result = new HashMap<>();
		
		if(userId == null) {
			result.put("code", 500);
			result.put("result", "error");
			return result;
		}
		
		
		return result;
	}
}
