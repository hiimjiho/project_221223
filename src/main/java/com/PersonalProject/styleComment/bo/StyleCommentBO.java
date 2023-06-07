package com.PersonalProject.styleComment.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PersonalProject.post.model.Paging;
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
	
	private static final int PAGE_LIMIT = 10; // 한 페이지당 보여줄 글 갯수
	
	private static final int BLOCK_LIMIT = 3; // 
	
	// 스타일 코멘트 작성
	public int addStyleComment(int userId, int styleId, String content) {
		return styleCommentMapper.insertStyleComment(userId, styleId, content);
	}
	
	// 스타일 코멘트 삭제
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
	
	public List<CommentView> generateCommentViewListByStyleId(int styleId, int page){
		
		int pageStart = (page -1) * PAGE_LIMIT;
		Map<String, Integer> pagingParams = new HashMap<>();
		pagingParams.put("start", pageStart);
		pagingParams.put("limit", PAGE_LIMIT);
		
		List<CommentView> commentViewList = new ArrayList<>();
		
		List<StyleComment> styleCommentList = styleCommentMapper.selectStyleCommentByStyleId(styleId, pageStart, PAGE_LIMIT);
		
		for(StyleComment styleComment : styleCommentList) {
			CommentView commentView = new CommentView();
			
			commentView.setStyleComment(styleComment);
			
			User user = userBO.getUserById(styleComment.getUserId());
			commentView.setUser(user);
			
			commentViewList.add(commentView);
		}
		return commentViewList;
	}
	
	public Paging pagingParam(int page, int styleId) {
		 // 전체 글 갯수 조회
       int boardCount = styleCommentMapper.countStyleCommentByStyleId(styleId);
       
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
	
	public void deleteCommentByStyleId(int StyleId) {
		styleCommentMapper.deleteCommentByStyleId(StyleId);
	}
	
	// 어드민 페이지 유저 탈퇴 기능에 사용할 스타일 코멘트 삭제
	public void deleteStyleCommentByUserId(int userId) {
		styleCommentMapper.deleteStyleCommentByUserId(userId);
	}
	
}
