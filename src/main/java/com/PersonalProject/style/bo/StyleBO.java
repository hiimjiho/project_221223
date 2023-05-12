package com.PersonalProject.style.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PersonalProject.style.dao.StyleMapper;
import com.PersonalProject.style.model.Style;

@Service
public class StyleBO {
	@Autowired
	private StyleMapper styleMapper;
	
	public List<Style> getStyleByProductId(int productId) {
		return styleMapper.selectStyleByProductId(productId);
	}
	
	public List<Style> getStyleByProductIdLimit5(int productId) {
		return styleMapper.selectStyleByProductIdLimit4(productId);
	}
}
