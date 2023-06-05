package com.PersonalProject.style.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.PersonalProject.style.model.Style;

@Repository
public interface StyleMapper {
	
	// 어드민 페이지 전용
	public List<Style> selectAdminStyleList();
	
	// 어드민 페이지 스타일 삭제
	public void adminDeleteStyleByStyleId(int styleId);
	
	public List<Style> selectStyleByProductId(
			@Param("productId")int productId,
			@Param("pageStart") int pageStart,
			@Param("pageLimit") int pageLimit);
	
	public int countStyleByProductId(int productId);
	
	public List<Style> selectStyleByProductIdLimit4(int productId);
	
	// 유저 프로필정보때 사용할 스타일 리스트
	public List<Style> selectStyleListByUserId(int userId);
	
	public int insertStyle(
			@Param("productId")int productId,
			@Param("content")String content,
			@Param("userId")int userId,			
			@Param("shoesImagePath")String shoesImagePath);
	
	public List<Style> selectStyleList();
	
	public int deleteStyleByStyleId(int styleId);
	
	public Style selectStyleByidUserId(
			@Param("styleId") int styleId,
			@Param("userId")int userId);
	
	// 게시글 삭제
	public void deleteStyleByUserIdStyleId(
			@Param("styleId") int styleId,
			@Param("userId")int userId);
	
	// 스타일 낱개 상세 페이지
	public Style selectStyleByStyleId(int styleId);
	
	// 스타일 뿌리기(페이징도 포함)
	public List<Style> selectStyle(
			@Param("pageStart") int pageStart,
			@Param("pageLimit") int pageLimit);
	
	// 스타일 리스트 페이징에 필요함
	public int countStyle();
	
	// 어드민 페이지 유저 삭제에 필요한 스타일
	public void deleteStyleByUserId(int userId);
	
}
