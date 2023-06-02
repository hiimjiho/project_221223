package com.PersonalProject.admin.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.PersonalProject.admin.model.Admin;

@Repository
public interface AdminMapper {
	public Admin selectAdminByAdminIdPassword(
			@Param("adminLoginId")String adminLoginId,
			@Param("adminPassword")String adminPassword);
}
