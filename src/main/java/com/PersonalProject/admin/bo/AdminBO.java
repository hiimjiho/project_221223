package com.PersonalProject.admin.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PersonalProject.admin.dao.AdminMapper;
import com.PersonalProject.admin.model.Admin;

@Service
public class AdminBO {
	@Autowired
	private AdminMapper adminMapper;
	
	public Admin getAdminByAdminIdPassword(String adminLoginId, String adminPassword) {
		return adminMapper.selectAdminByAdminIdPassword(adminLoginId, adminPassword);
	}
}
