package com.PersonalProject.review.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PersonalProject.post.model.Paging;
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
	
	private static final int PAGE_LIMIT = 5; // 한 페이지당 보여줄 글 갯수
	
	private static final int BLOCK_LIMIT = 10; // 
	
	
	public List<ReviewCard> generateReview(int productId, Integer userId, int page){
		
		int pageStart = (page -1) * PAGE_LIMIT;
		Map<String, Integer> pagingParams = new HashMap<>();
		pagingParams.put("start", pageStart);
		pagingParams.put("limit", PAGE_LIMIT);
		
		List<ReviewCard> reviewCard = new ArrayList<>();
		
		// 한줄평 리스트 (신발에 해당하는)
		List<Review> reviewList = reviewMapper.selectReviewListByProductId(productId, pageStart, PAGE_LIMIT);
		
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
	
	public Paging pagingParamByProductId(int page, int productId) {
		 // 전체 글 갯수 조회
       int boardCount = reviewMapper.reviewCountByProductId(productId);
       
       // 전체 페이지 갯수 계산(10/3=3.33333 => 4)
       int maxPage = (int) (Math.ceil((double) boardCount / PAGE_LIMIT));
       
       // 시작 페이지 값 계산(1, 4, 7, 10, ~~~~)
       int startPage = (((int)(Math.ceil((double) page / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1;
       
       // 끝 페이지 값 계산(3, 6, 9, 12, ~~~~)
       int endPage = startPage + BLOCK_LIMIT - 1;
       
       if (endPage > maxPage) {
           endPage = maxPage;
       }
       Paging paging = new Paging();
       
       paging.setPage(page);
       paging.setMaxPage(maxPage);
       paging.setStartPage(startPage);
       paging.setEndPage(endPage);
       return paging;
	}
}
