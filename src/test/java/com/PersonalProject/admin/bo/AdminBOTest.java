package com.PersonalProject.admin.bo;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class AdminBOTest {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	AdminBO adminBO;
	
	//@Test
	void 어드민회원가입() {
		logger.info("######어드민 로그인######");
		adminBO.getAdminByAdminIdPassword("aa", "aaaaa");
	}
	
	//@Test
	void test() {
		fail("Not yet implemented");
	}
	
	//@Test
	void 유저삭제() {
		logger.info("#####유저 삭제#######");
		adminBO.deleteUserByUserId(7);
	}
	
	//@Test
	void 스타일삭제() {
		logger.info("#######스타일 삭제########");
		adminBO.deleteStyleByStyleId(27);
	}
	
	@Test
	void 신발삭제() {
		logger.info("###########신발 삭제###########");
		adminBO.deleteProductByProductId(19);
	}

}
