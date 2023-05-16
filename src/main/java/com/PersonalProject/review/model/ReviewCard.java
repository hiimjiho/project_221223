package com.PersonalProject.review.model;

import java.util.List;

import com.PersonalProject.user.model.User;

public class ReviewCard {
	private User user;
	private Review review;
	private List<Review> reviewList;
	
	
	public List<Review> getReviewList() {
		return reviewList;
	}
	public void setReviewList(List<Review> reviewList) {
		this.reviewList = reviewList;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Review getReview() {
		return review;
	}
	public void setReview(Review review) {
		this.review = review;
	}
	
	
	
}
