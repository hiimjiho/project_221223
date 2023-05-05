package com.PersonalProject.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@PostMapping("/is_duplicated_id")
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
	
	@PostMapping("/is_duplicated_nickname")
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
}
