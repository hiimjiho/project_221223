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
		String shoesImagePath = null;
		if(file != null) {
			shoesImagePath = fileManager.saveFile(shoesImagePath, file);
		}
		return styleMapper.insertStyle(productId, content, userId, shoesImagePath);
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
			
			card.setLikeCount();
			
		}
		
		
	}
	
}
