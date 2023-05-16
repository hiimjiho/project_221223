package com.PersonalProject.review.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PersonalProject.review.dao.ReviewMapper;
import com.PersonalProject.review.model.Review;
import com.PersonalProject.review.model.ReviewCard;
import com.PersonalProject.user.bo.UserBO;
import com.PersonalProject.user.model.User;

@Service
public class ReviewBO {
	@Autowired
	private ReviewMapper reviewMapper;
	@Autowired
	private UserBO userBO;
	
	public int addReview(int userId, int productId, String content) {
		return reviewMapper.insertReview(userId, productId, content);
	}
	
	public List<Review> getReviewList(){
		return reviewMapper.selectReviewList();
	}
	
	public int deleteReview(int reviewId) {
		return reviewMapper.deleteReviewById(reviewId);
	}
	
	
	public List<ReviewCard> generateReview(int productId){
		List<ReviewCard> reviewCard = new ArrayList<>();
		
		// 한줄평 리스트 (신발에 해당하는)
		List<Review> reviewList = reviewMapper.selectReviewListByProductId(productId);
		
		for(Review review : reviewList) {
			ReviewCard card = new ReviewCard();
			
			// 한줄평 한개
			card.setReview(review);
			
			// 평을 쓴 유저 정보
			User user = userBO.getUserById(review.getUserId());
			card.setUser(user);
			
			reviewCard.add(card);
			
		}
		return reviewCard;
	}
}
