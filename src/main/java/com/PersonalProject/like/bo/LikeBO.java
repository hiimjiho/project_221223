package com.PersonalProject.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PersonalProject.like.dao.LikeMapper;

@Service
public class LikeBO {
	@Autowired
	private LikeMapper likeMapper;
	
	public void likeToggole(int userId, int styleId) {
		
		int likeCount = likeMapper.likeCountByUserIdStyleId(styleId, userId);
		
		if(likeCount > 0) {
			likeMapper.likeDeleteByUserIdStyleId(styleId, userId);
		}else {
			likeMapper.likeInsertByUserIdStyleId(styleId, userId);
		}
	}
	
	
}
