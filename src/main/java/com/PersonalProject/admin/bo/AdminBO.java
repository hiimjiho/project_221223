package com.PersonalProject.admin.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PersonalProject.admin.dao.AdminMapper;
import com.PersonalProject.admin.model.Admin;
import com.PersonalProject.common.FileManagerService;
import com.PersonalProject.like.bo.LikeBO;
import com.PersonalProject.style.bo.StyleBO;
import com.PersonalProject.style.model.Style;
import com.PersonalProject.styleComment.bo.StyleCommentBO;
import com.PersonalProject.user.bo.UserBO;

@Service
public class AdminBO {
	@Autowired
	private AdminMapper adminMapper;

	@Autowired
	private UserBO userBO;
	
	@Autowired
	private StyleBO styleBO;
	
	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private StyleCommentBO styleCommentBO;

	@Autowired
	private FileManagerService fileManager;

	public Admin getAdminByAdminIdPassword(String adminLoginId, String adminPassword) {
		return adminMapper.selectAdminByAdminIdPassword(adminLoginId, adminPassword);
	}

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

}
