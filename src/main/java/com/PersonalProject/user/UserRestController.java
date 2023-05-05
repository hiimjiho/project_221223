package com.PersonalProject.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PersonalProject.user.bo.UserBO;
import com.PersonalProject.user.model.User;
@RequestMapping("/user")
@RestController
public class UserRestController {
	@Autowired
	private UserBO userBO;
	
	@GetMapping("/is_duplicated_id")
	public Map<String, Object> isDuplicatedId(
			@RequestParam("loginId") String loginId){
		
		User user = userBO.getUserByLoginId(loginId);
		
		Map<String, Object> result = new HashMap<>();
		if(user != null) {
			result.put("code", 1);	
			result.put("result", true);
		}else {
			result.put("code", 1);
			result.put("result", false);
		}
		return result;
	}
	
	@GetMapping("/is_duplicated_nickname")
	public Map<String, Object> isDuplicatedNickname(
			@RequestParam("nickname") String nickname){
		
		Map<String, Object> result = new HashMap<>();
		User user = userBO.getUserByNickname(nickname);
		if(user != null) {
			result.put("code", 1);
			result.put("result", true);
		}else {
			result.put("code", 1);
			result.put("result", false);
		}
		return result;
	}
	
	@PostMapping("/sign_up")
	public Map<String, Object> signUp(
			@RequestParam("loginId") String loginId,
			@RequestParam("nickname") String nickname,
			@RequestParam("password") String password,
			@RequestParam("email") String email){
		
		Map<String, Object> result = new HashMap<>();
		userBO.insertMembership(loginId, nickname, password, email);
		result.put("code", 1);
		result.put("result", "성공");
		
		return result;
	}
	
}
