package com.PersonalProject.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.PersonalProject.post.bo.PostBO;

@RequestMapping("/post")
@RestController
public class PostRestController {
	@Autowired
	private PostBO postBO;
	
	@PostMapping("/create")
	public Map<String, Object> postCreate(
			HttpSession session,
			@RequestParam("subject") String subject,
			@RequestParam(value="content", required=false) String content,
			@RequestParam(value="file", required=false)MultipartFile file){
		
		int userId = (int)session.getAttribute("userId");
		String loginId = (String)session.getAttribute("userLoginId");
		
		postBO.addPost(userId, loginId, subject, content, file);
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("result", "성공");
		return result;
	}
	@PutMapping("/update")
	public Map<String, Object> postUpdate(HttpSession session,
			@RequestParam("postId") int postId,
			@RequestParam("subject") String subject,
			@RequestParam(value="content", required=false) String content,
			@RequestParam(value="file", required=false) MultipartFile file){
		
		int userId = (int)session.getAttribute("userId");
		String userLoginId = (String)session.getAttribute("userLoginId");
		
		postBO.updatePost(userId, userLoginId, postId, subject, content, file);
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("result", "성공");
		return result;
	}
	
	@DeleteMapping("/delete")
	public Map<String, Object> postDelete(HttpSession session,
			@RequestParam("postId") int postId){
		
		int userId = (int)session.getAttribute("userId");
		Map<String, Object> result = new HashMap<>();
		int rowCount = postBO.deletePostByPostidUserId(postId, userId);
		if(rowCount > 0) {
			result.put("code", 1);
			result.put("result", "성공");
			return result;
		}else {
			result.put("code", 500);
			result.put("result", "errorMessage");
			return result;
		}
		
	}
}
