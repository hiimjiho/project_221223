package com.PersonalProject.style.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.PersonalProject.style.model.Style;

@Repository
public interface StyleMapper {
	public List<Style> selectStyleByProductId(int productId);
	
	public List<Style> selectStyleByProductIdLimit4(int productId);
	
	public int insertStyle(
			@Param("productId")int productId,
			@Param("content")String content,
			@Param("userId")int userId,			
			@Param("shoesImagePath")String shoesImagePath);
	
}
