package com.PersonalProject.styleComment.model;

import com.PersonalProject.user.model.User;

public class CommentView {
	
	private StyleComment styleComment;
	
	private User user;

	public StyleComment getStyleComment() {
		return styleComment;
	}

	public void setStyleComment(StyleComment styleComment) {
		this.styleComment = styleComment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
