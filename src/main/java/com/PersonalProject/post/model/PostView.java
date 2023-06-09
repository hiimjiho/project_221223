package com.PersonalProject.post.model;

import com.PersonalProject.postComment.model.PostComment;
import com.PersonalProject.user.model.User;

public class PostView {
	private Post post;
	
	private User user;
	
	private PostComment postComment;

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

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
	
}
