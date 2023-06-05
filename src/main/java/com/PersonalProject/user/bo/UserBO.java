package com.PersonalProject.user.bo;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.PersonalProject.common.FileManagerService;
import com.PersonalProject.user.dao.UserMapper;
import com.PersonalProject.user.model.User;

@Service
public class UserBO {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private FileManagerService fileManager;
	
	public User getUserByLoginId(String loginId) {
		return userMapper.selectUserByLoginId(loginId);
	}
	
	public User getUserByNickname(String nickname) {
		return userMapper.selectUserByNickname(nickname);
	}
	
	public int insertMembership(String loginId, String nickname, String password, String email) {
		return userMapper.insertMembership(loginId, nickname, password, email);
	}
	
	public User getUserByLoginIdPassword(String loginId, String password){
		return userMapper.selectUserByLoginIdPassword(loginId, password);
	}
	
	public User getUserByIntegerId(Integer userId) {
		return userMapper.selectUserByIntegerId(userId);
	}
	
	public User getUserById(int id) {
		return userMapper.selectUserById(id);
	}
	
	public void updateUser(
			MultipartFile file,
			int userId,
			String loginId,
			String nickname) {
		
		// 해당 유저 가져오기
		User user = userMapper.selectUserById(userId);
		if(user == null) {
			logger.warn("[update post] post is null. postId:{}, userId:{}", userId);
		}
		
		String profileImagePath = null;
		if(file != null) {
			// 업로드
			profileImagePath = fileManager.saveFile(loginId, file);
			
			if(profileImagePath != null && user.getProfileImagePath() != null) {
				fileManager.deleteFile(user.getProfileImagePath());
			}
		}
		userMapper.updateUser(profileImagePath, userId, nickname);
	}
	
	// 어드민 페이지 유저 관리용
	public List<User> getUserList(){
		return userMapper.selectUserList();
	}
	
	public void deleteUserByUserId(int userId) {
		User user = userMapper.selectUserByUserId(userId);
		if(user.getProfileImagePath() != null) {
			fileManager.deleteFile(user.getProfileImagePath());
		}
		userMapper.deleteUserByUserId(userId);
	}
	
	// 어드민 페이지 유저 관리
	public User getUserByUserId(int userId) {
		return userMapper.selectUserByUserId(userId);
	}
}
