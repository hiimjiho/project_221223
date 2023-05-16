package com.PersonalProject.styleComment.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PersonalProject.styleComment.dao.StyleCommentMapper;

@Service
public class StyleCommentBO {
	@Autowired
	private StyleCommentMapper styleCommentMapper;
	
	public int addStyleComment(int userId, int styleId, String content) {
		return styleCommentMapper.insertStyleComment(userId, styleId, content);
	}
	
	public int deleteStyleComment(int styleId, int styleCommentId) {
		return styleCommentMapper.deleteStyleComment(styleId, styleCommentId);
	}
}
