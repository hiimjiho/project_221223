package com.PersonalProject.post.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.PersonalProject.common.FileManagerService;
import com.PersonalProject.post.dao.PostMapper;
import com.PersonalProject.post.model.Post;
import com.PersonalProject.post.model.PostView;
import com.PersonalProject.user.bo.UserBO;
import com.PersonalProject.user.model.User;

@Service
public class PostBO {
	@Autowired
	private PostMapper postMapper;
	
	@Autowired
	private UserBO userBO;
	
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
	
	public List<PostView> generatePostList() {
		
		List<PostView> postViewList = new ArrayList<>();
		
		List<Post> postList = postMapper.selectPostList();
		
		for(Post post : postList) {
			PostView postView = new PostView();
			
			postView.setPost(post);
			
			User user = userBO.getUserById(post.getUserId());
			
			postView.setUser(user);
			
			postViewList.add(postView);
		}
		return postViewList;
	}
	public PostView generatePostViewByPostId(int postId) {
		PostView postView = new PostView();
		
		Post post = postMapper.selectPost(postId);
		
		postView.setPost(post);
		
		User user = userBO.getUserById(post.getUserId());
		
		postView.setUser(user);
		
		return postView;
		
	}

}
