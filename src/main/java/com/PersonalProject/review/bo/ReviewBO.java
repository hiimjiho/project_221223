package com.PersonalProject.review.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PersonalProject.review.dao.ReviewMapper;
import com.PersonalProject.review.model.Review;

@Service
public class ReviewBO {
	@Autowired
	private ReviewMapper reviewMapper;
	
	public int addReview(int userId, int productId, String content) {
		return reviewMapper.insertReview(userId, productId, content);
	}
	
	public List<Review> getReviewList(){
		return reviewMapper.selectReviewList();
	}
}
