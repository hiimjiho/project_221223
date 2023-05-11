package com.PersonalProject.style.dao;

import org.springframework.stereotype.Repository;

import com.PersonalProject.style.model.Style;

@Repository
public interface StyleMapper {
	public Style selectStyleByProductId(int productId);
}
