package com.PersonalProject.admin.bo;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

class AdminBOTest {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	AdminBO adminBO;
	
	@Test
	void 어드민회원가입() {
		logger.info("######어드민 로그인######");
		adminBO.getAdminByAdminIdPassword("aa", "aaaaa");
	}
	
	//@Test
	void test() {
		fail("Not yet implemented");
	}

}
