package com.PersonalProject.postComment.model;

import java.util.List;

import com.PersonalProject.user.model.User;

public class PostCommentView {
	private User user;
	
	private PostComment postComment;
	
	private List<PostComment> postCommentList;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PostComment getPostComment() {
		return postComment;
	}

	public void setPostComment(PostComment postComment) {
		this.postComment = postComment;
	}

	public List<PostComment> getPostCommentList() {
		return postCommentList;
	}

	public void setPostCommentList(List<PostComment> postCommentList) {
		this.postCommentList = postCommentList;
	}
	
	
	
	
	
	
	
}
