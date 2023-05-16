package com.PersonalProject.styleComment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PersonalProject.styleComment.bo.StyleCommentBO;

@RequestMapping("/style_comment")
@RestController
public class StyleCommentRestController {
	@Autowired
	private StyleCommentBO styleCommentBO;
	
	@PostMapping("/create")
	public Map<String, Object> styleCommentCreate(
			HttpSession session,
			@RequestParam("styleId") int styleId,
			@RequestParam("content") String content){
		
		Integer userId = (Integer)session.getAttribute("userId");
		Map<String, Object> result = new HashMap<>();
		
		if(userId == null) {
			result.put("code", 500);
			result.put("result", "error");
			result.put("result", "errorMessage");
			return result;
		}
		styleCommentBO.addStyleComment(userId, styleId, content);
		result.put("code", 1);
		result.put("result", "성공");
		return result;
	}
	
	@DeleteMapping("/delete")
	public Map<String, Object> styleCommentDelete(
			HttpSession session,
			@RequestParam("styleId") int styleId,
			@RequestParam("id") int styleCommentId){
		
		Integer userId = (Integer)session.getAttribute("userId");
		Map<String, Object> result = new HashMap<>();
		if(userId == null) {
			result.put("code", 500);
			result.put("result", "error");
			result.put("result", "errorMessage");
			return result;
		}
		styleCommentBO.deleteStyleComment(styleId, styleCommentId);
		result.put("code", 1);
		result.put("result", "성공");
		return result;
	}
}
