package com.PersonalProject.post.bo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PostMapper postMapper;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private FileManagerService fileManager;
	
	// 프로필 화면 구성할 때 쓸 포스트 리스트
	public List<Post> getPostListByUserId(int userId){
		return postMapper.selectPostListByUserId(userId);
	};
	
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
	
	// 코멘트 수정을 위한 메서드
	public Post getPostByPostId(int postId) {
		return postMapper.selectPostByPostId(postId);
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
	
	public Post getPostByPostIdUserId(int postId, int userId) {
		return postMapper.selectPostByPostIdUserId(postId, userId);
	}
	
	public void updatePost(
			int userId,
			String loginId,
			int postId,
			String subject,
			String content,
			MultipartFile file) {
		
		// 해당 글 가져오기
		Post post = getPostByPostIdUserId(postId, userId);
		logger.warn("[update post] post is null. postId:{}, userId:{}", postId, userId);
		if(post == null) {
			logger.warn("[update post] post is null. postId:{}, userId:{}", postId, userId);
		}
		
		String imagePath = null;
		if(file != null) {
			// 업로드
			imagePath = fileManager.saveFile(loginId, file);
			
			if(imagePath != null && post.getImagePath() != null) {
				fileManager.deleteFile(post.getImagePath());
			}
		}
		
		postMapper.updatePostByPostId(postId, subject, content, imagePath);
	}
	
	public int deletePostByPostIdUserId(int postId, int userId) {
		// 해당 글 가져오기
		Post post = getPostByPostIdUserId(postId, userId);
		if(post == null) {
			logger.error("[글 삭제] post is null. postId:{}", postId, userId);
			return 0;
		}
		
		// 이미지 삭제
		if(post.getImagePath() != null) {
			fileManager.deleteFile(post.getImagePath());
		}
		
		return postMapper.deletePostByPostIdUserId(postId, userId);
	}

}
