package com.PersonalProject.postComment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PersonalProject.postComment.dao.PostCommentMapper;
import com.PersonalProject.postComment.model.PostComment;
import com.PersonalProject.postComment.model.PostCommentView;
import com.PersonalProject.user.bo.UserBO;
import com.PersonalProject.user.model.User;

@Service
public class PostCommentBO {
	@Autowired
	private PostCommentMapper postCommentMapper;
	
	@Autowired
	private UserBO userBO;
	
	public int addPostCommentByContentPostId(int postId, int userId, String content) {
		return postCommentMapper.addPostCommentByContentPostId(postId, userId, content);
	}
	
	public List<PostComment> getPostCommentListByPostId(int postId){
		return postCommentMapper.selectPostCommentListByPostId(postId);
	}
	
	public int deletePostCommentByCommentId(int postCommentId) {
		return postCommentMapper.deletePostCommentByCommentId(postCommentId);
	}
	
	public List<PostCommentView> generatePostCommentByPostId(int postId){
		List<PostCommentView> CommenView = new ArrayList<>();
		
		List<PostComment> postCommentList = getPostCommentListByPostId(postId);
		
		for(PostComment postComment : postCommentList) {
			PostCommentView postCommentView = new PostCommentView();
			
			postCommentView.setPostComment(postComment);
			
			User user = userBO.getUserById(postComment.getUserId());
			
			postCommentView.setUser(user);
			
			CommenView.add(postCommentView);
		}
		return CommenView;
	}
	
	public List<PostComment> getPostCommentByPostIdUserIdPostCommentId(int postId, int userId, int postCommentId) {
		return postCommentMapper.selectPostCommentByPostIdUserIdPostCommentId(postId, userId, postCommentId);
	}
	
	// 포스트에 있는 댓글 삭제하기
	public void deletePostCommentByPostId(int postId) {
		postCommentMapper.deletePostCommentByPostId(postId);;
	}
	
	// 어드민 페이지 유저 삭제 기능에 사용할 포스트 코멘트 삭제
	public void deleteCommentByUserId(int userId) {
		postCommentMapper.deleteCommentByUserId(userId);
	}
	
}
