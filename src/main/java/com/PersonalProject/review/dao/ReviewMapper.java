package com.PersonalProject.review.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.PersonalProject.review.model.Review;

@Repository
public interface ReviewMapper {
	public int insertReview(
			@Param("userId")int userId,
			@Param("productId")int productId,			
			@Param("content")String content);
	
	public List<Review> selectReviewList();
	
	public int deleteReviewById(int reviewId);
	
	public List<Review> selectReviewListByProductId(
			@Param("productId")int productId,
			@Param("pageStart") int pageStart,
			@Param("pageLimit") int pageLimit);
	
	public int reviewCountByProductId(int productId);
	
	public void deleteReviewByProductId(int productId);
}
