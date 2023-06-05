package com.PersonalProject.admin.bo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PersonalProject.admin.dao.AdminMapper;
import com.PersonalProject.admin.model.Admin;
import com.PersonalProject.common.FileManagerService;
import com.PersonalProject.favorite.bo.FavoriteBO;
import com.PersonalProject.like.bo.LikeBO;
import com.PersonalProject.post.bo.PostBO;
import com.PersonalProject.post.model.Post;
import com.PersonalProject.postComment.bo.PostCommentBO;
import com.PersonalProject.product.bo.ProductBO;
import com.PersonalProject.review.bo.ReviewBO;
import com.PersonalProject.style.bo.StyleBO;
import com.PersonalProject.style.model.Style;
import com.PersonalProject.styleComment.bo.StyleCommentBO;
import com.PersonalProject.user.bo.UserBO;

@Service
public class AdminBO {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AdminMapper adminMapper;
	
	@Autowired
	private ProductBO productBO;
	
	@Autowired
	private ReviewBO reviewBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private StyleBO styleBO;
	
	@Autowired
	private LikeBO likeBO;
	
	@Autowired 
	private PostBO postBO;
	
	@Autowired 
	private PostCommentBO postCommentBO;
	
	@Autowired
	private StyleCommentBO styleCommentBO;

	@Autowired
	private FileManagerService fileManager;
	
	@Autowired
	private FavoriteBO favoriteBO;

	public Admin getAdminByAdminIdPassword(String adminLoginId, String adminPassword) {
		return adminMapper.selectAdminByAdminIdPassword(adminLoginId, adminPassword);
	}

	// 스타일 삭제 기능
	public void deleteStyleByStyleId(int styleId) {
		// 해당 스타일을 가져온다.
		Style style = styleBO.getStyleByStyleId(styleId);

		// 이미지 삭제
		fileManager.deleteFile(style.getShoesImagePath());

		// 댓글 삭제
		styleCommentBO.deleteCommentByStyleId(styleId);

		// 좋아요 삭제
		likeBO.deleteLikeByStyleId(styleId);

		// 게시글 삭제
		styleBO.adminDeleteStyleByStyleId(styleId);
	}
	
	// 포스트 삭제 기능
	public int deletePostByPostId(int postId) {
		// 해당 포스트
		Post post = postBO.getPostByPostId(postId);
		
		if(post == null) {
			logger.error("[글 삭제] post is null. postId:{}", postId);
			return 0;
		}
		
		// 이미지 삭제
		if(post.getImagePath() != null) {
			fileManager.deleteFile(post.getImagePath());
		}
		
		//댓글 삭제
		postCommentBO.deletePostCommentByPostId(postId);
		
		return postBO.deletePostByPostId(postId);	
	}
	
	// 신발 삭제 기능
	public int deleteProductByProductId(int productId) {
		
		// 한줄평 삭제
		reviewBO.deleteReviewByProductId(productId);
		
		return productBO.deleteProductByProductId(productId);
		
	}
	
	public void deleteUserByUserId(int userId) {
		// 해당 유저의 리뷰
		reviewBO.deleteReviewByUserId(userId);
		// 해당 유저의 포스트
		postBO.deletePostByUserId(userId);
		// 해당 유저의 포스트 댓글
		postCommentBO.deleteCommentByUserId(userId);
		// 해당 유저의 스타일
		styleBO.deleteStyleByUserId(userId);
		// 해당 유저의 스타일 댓글
		styleCommentBO.deleteStyleCommentByUserId(userId);
		// 해당 유저의 즐겨찾기 삭제
		favoriteBO.deleteFavoriteByUserId(userId);
		
		// 유저 삭제
		userBO.deleteUserByUserId(userId);
	}
}
