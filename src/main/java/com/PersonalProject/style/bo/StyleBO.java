package com.PersonalProject.style.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.PersonalProject.common.FileManagerService;
import com.PersonalProject.like.bo.LikeBO;
import com.PersonalProject.like.dao.LikeMapper;
import com.PersonalProject.like.model.Like;
import com.PersonalProject.product.bo.ProductBO;
import com.PersonalProject.product.model.Product;
import com.PersonalProject.style.dao.StyleMapper;
import com.PersonalProject.style.model.Style;
import com.PersonalProject.style.model.StyleCard;
import com.PersonalProject.styleComment.bo.StyleCommentBO;
import com.PersonalProject.styleComment.model.CommentView;
import com.PersonalProject.user.bo.UserBO;
import com.PersonalProject.user.model.User;

@Service
public class StyleBO {
	@Autowired 
	private ProductBO productBO;
	
	@Autowired
	private StyleMapper styleMapper;
	
	@Autowired
	private FileManagerService fileManager;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private StyleCommentBO styleCommentBO;
	
	@Autowired
	private LikeMapper likeMapper;
	
	@Autowired
	private LikeBO likeBO;
	
	public List<Style> getStyleByProductId(int productId) {
		return styleMapper.selectStyleByProductId(productId);
	}
	
	public List<Style> getStyleByProductIdLimit5(int productId) {
		return styleMapper.selectStyleByProductIdLimit4(productId);
	}
	
	public int addStyle(int productId, String content, int userId, MultipartFile file) {
		String imagePath = null;
		if(file != null) {
			imagePath = fileManager.saveFile(imagePath, file);
		}
		return styleMapper.insertStyle(productId, content, userId, imagePath);
	}
	
	public List<StyleCard> generateStyleCard(Integer userId){
		List<StyleCard> styleCard = new ArrayList<>();
		
		List<Style> styleList = styleMapper.selectStyleList();
		
		for(Style style : styleList) {
			StyleCard card = new StyleCard();
			
			// 글
			card.setStyle(style);
			
			// 글쓴 사람 정보
			User user = userBO.getUserById(style.getUserId());
			card.setUser(user);
			
			// 댓글들(styleId)
			List<CommentView> commentViewList = styleCommentBO.generateCommentViewList(style.getId());
			card.setCommentList(commentViewList);
			
			// 좋아요 눌렀는지 여부
			if(userId == null) {
				
				card.setHetherLike(false);
			}else {
				// 좋아요 누른 거
				Like like = likeMapper.selectLike(style.getId(), userId);
				if(like == null) {
					card.setHetherLike(false);
				}else {
					card.setHetherLike(true);
				}
			}
			
			card.setLikeCount(likeBO.likeCountByStyleIdUserId(style.getId()));
			styleCard.add(card);
		}
		return styleCard;
		
	}
	

	public List<StyleCard> generateStyleCardByProductId(int productId, Integer userId){
		List<StyleCard> styleCard = new ArrayList<>();
		
		List<Style> styleList = styleMapper.selectStyleList();
		
		for(Style style : styleList) {
			StyleCard card = new StyleCard();
			
			// 신발 정보
			Product product = productBO.getProductByProductId(productId);
			card.setProduct(product);
			
			// 글
			card.setStyle(style);
			
			// 글쓴 사람 정보
			User user = userBO.getUserById(style.getUserId());
			card.setUser(user);
			
			// 댓글들(styleId)
			List<CommentView> commentViewList = styleCommentBO.generateCommentViewList(style.getId());
			card.setCommentList(commentViewList);
			
			// 좋아요 눌렀는지 여부
			if(userId == null) {
				
				card.setHetherLike(false);
			}else {
				// 좋아요 누른 거
				Like like = likeMapper.selectLike(style.getId(), userId);
				if(like == null) {
					card.setHetherLike(false);
				}else {
					card.setHetherLike(true);
				}
			}
			
			card.setLikeCount(likeBO.likeCountByStyleIdUserId(style.getId()));
			styleCard.add(card);
		}
		return styleCard;
		
	}
	
	
	
	
	public Style getStyleByidUserId(int styleId, int userId) {
		return styleMapper.selectStyleByidUserId(styleId, userId);
	}
	
	// 게시글 삭제
	public void deleteStyleByStyleIdUserId(int styleId, int userId) {
		// 삭제할 때 좋아요 갯수와 댓글 내용 사진 모두 삭제해 주어야 한다.
		
		// 해당 스타일을 가져온다.
		Style style = getStyleByidUserId(styleId, userId);
		
		// 이미지 삭제
		fileManager.deleteFile(style.getShoesImagePath());
		
		// 댓글 삭제
		styleCommentBO.deleteCommentByStyleId(styleId);
		
		// 좋아요 삭제
		likeBO.deleteLikeByStyleId(styleId);
		
		// 게시글 삭제
		styleMapper.deleteStyleByUserIdStyleId(styleId, userId);
		
	}
	
}
