package com.PersonalProject.post.model;

import com.PersonalProject.user.model.User;

public class PostView {
	private Post post;
	
	private User user;

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
	
}
