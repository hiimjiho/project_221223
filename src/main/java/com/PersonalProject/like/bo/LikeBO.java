package com.PersonalProject.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PersonalProject.like.dao.LikeMapper;

@Service
public class LikeBO {
	@Autowired
	private LikeMapper likeMapper;
	
	// 좋아요 기능 토글
	public void likeToggole(int userId, int styleId) {
		
		int likeCount = likeMapper.likeCountByUserIdStyleId(styleId, userId);
		
		if(likeCount > 0) {
			likeMapper.likeDeleteByUserIdStyleId(styleId, userId);
		}else {
			likeMapper.likeInsertByUserIdStyleId(styleId, userId);
		}
	}
	
	public int likeCountByStyleIdUserId(int styleId) {
		return likeMapper.likeCountByStyleIdUserId(styleId, null);
	}
	
	public void deleteLikeByStyleId(int styleId) {
		likeMapper.deleteLikeByStyleId(styleId);
	}
	
	// 어드민 페이지에 사용할 좋아요 삭제
	public void deleteLikeByUserId(int userId) {
		likeMapper.deleteLikeByUserId(userId);
	}
	
}
