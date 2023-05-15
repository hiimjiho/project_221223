package com.PersonalProject.style.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.PersonalProject.common.FileManagerService;
import com.PersonalProject.style.dao.StyleMapper;
import com.PersonalProject.style.model.Style;

@Service
public class StyleBO {
	@Autowired
	private StyleMapper styleMapper;
	
	@Autowired
	private FileManagerService fileManager;
	
	public List<Style> getStyleByProductId(int productId) {
		return styleMapper.selectStyleByProductId(productId);
	}
	
	public List<Style> getStyleByProductIdLimit5(int productId) {
		return styleMapper.selectStyleByProductIdLimit4(productId);
	}
	
	public int addStyle(int productId, String content, int userId, MultipartFile file) {
		String shoesImagePath = null;
		if(file != null) {
			shoesImagePath = fileManager.saveFile(shoesImagePath, file);
		}
		return styleMapper.insertStyle(productId, content, userId, shoesImagePath);
	}
}
