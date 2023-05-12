package com.PersonalProject.style.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.PersonalProject.style.model.Style;

@Repository
public interface StyleMapper {
	public List<Style> selectStyleByProductId(int productId);
	
	public List<Style> selectStyleByProductIdLimit4(int productId);
}
