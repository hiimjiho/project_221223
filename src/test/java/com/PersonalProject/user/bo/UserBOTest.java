package com.PersonalProject.user.bo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.PersonalProject.user.model.User;
@SpringBootTest	// 스프링 수행
class UserBOTest {

	@Autowired
	UserBO userBO;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Transactional	// insert 후 다시 롤백
	//@Test
	void 회원가입() {
		logger.info("#### 회원가입");
		userBO.insertMembership("test567", "bbbb", "bbb", "bbbb@bbbb.com");
	}
	
	@Test
	void 유저가져오기() {
		User user = userBO.getUserByLoginId("aaaaa");
		assertNotNull(user);
	}
	
	//@Test
	void test() {
		fail("Not yet implemented");
	}
	


}
