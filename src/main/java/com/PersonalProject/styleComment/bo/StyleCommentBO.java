package com.PersonalProject.styleComment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PersonalProject.styleComment.dao.StyleCommentMapper;
import com.PersonalProject.styleComment.model.CommentView;
import com.PersonalProject.styleComment.model.StyleComment;
import com.PersonalProject.user.bo.UserBO;
import com.PersonalProject.user.model.User;

@Service
public class StyleCommentBO {
	@Autowired
	private StyleCommentMapper styleCommentMapper;
	
	@Autowired
	private UserBO userBO;
	
	public int addStyleComment(int userId, int styleId, String content) {
		return styleCommentMapper.insertStyleComment(userId, styleId, content);
	}
	
	public int deleteStyleComment(int styleCommentId) {
		return styleCommentMapper.deleteStyleComment(styleCommentId);
	}
	
	public List<StyleComment> getStyleComment(){
		return styleCommentMapper.selectStyleComment();
	}
	
	public List<CommentView> generateCommentViewList(int styleId){
		
		List<CommentView> commentViewList = new ArrayList<>();
		
		List<StyleComment> styleCommentList = styleCommentMapper.selectStyleCommentListByStyleId(styleId);
		
		for(StyleComment styleComment : styleCommentList) {
			CommentView commentView = new CommentView();
			
			commentView.setStyleComment(styleComment);
			
			User user = userBO.getUserById(styleComment.getUserId());
			commentView.setUser(user);
			
			commentViewList.add(commentView);
		}
		return commentViewList;
	}
}
