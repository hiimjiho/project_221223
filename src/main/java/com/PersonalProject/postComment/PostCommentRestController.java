package com.PersonalProject.postComment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PersonalProject.postComment.bo.PostCommentBO;
@RequestMapping("/post_comment")
@RestController
public class PostCommentRestController {
	@Autowired
	private PostCommentBO postCommentBO;
	
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
		postCommentBO.addPostCommentByContentPostId(postId, userId, content);
		result.put("code", 1);
		result.put("result", "성공");
		return result;
	}
	
	@DeleteMapping("/delete")
	public Map<String, Object> postCommentDelete(
			@RequestParam("postCommentId") int postCommentId,
			HttpSession session){
		Integer userId = (Integer)session.getAttribute("userId");
		
		Map<String, Object> result = new HashMap<>();
		
		if(userId == null) {
			result.put("code", 500);
			result.put("result", "error");
			return result;
		}
		
		postCommentBO.deletePostCommentByCommentId(postCommentId);
		result.put("code", 1);
		result.put("result", "성공");
		return result;
	}
}
