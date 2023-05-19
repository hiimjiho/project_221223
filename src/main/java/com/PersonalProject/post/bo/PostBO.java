package com.PersonalProject.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.PersonalProject.common.FileManagerService;
import com.PersonalProject.post.dao.PostMapper;
import com.PersonalProject.post.model.Post;

@Service
public class PostBO {
	@Autowired
	private PostMapper postMapper;
	
	@Autowired
	private FileManagerService fileManager;
	
	public int addPost(int userId, String loginId, String subject, String content, MultipartFile file) {
		String imagePath = null;
		if(file != null) {
			// 서버에 이미지 업로드 후 imagePath 받아옴
			imagePath = fileManager.saveFile(loginId, file);
		}
		return postMapper.insertPost(userId, loginId, subject, content, imagePath);
	}
	
	public List<Post> getPostList(){
		return postMapper.selectPostList();
	}

}
