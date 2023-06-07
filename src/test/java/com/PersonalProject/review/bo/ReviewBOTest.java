package com.PersonalProject.review.bo;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
@SpringBootTest
class ReviewBOTest {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ReviewBO reviewBO;
	
	//@Test
	void test() {
		fail("Not yet implemented");
	}
	@Transactional
	//@Test
	void 리뷰남기기() {
		logger.info("######리뷰 남기기######");
		reviewBO.addReview(6, 10, "ㅎ");
	}
	
	@Test
	void 리뷰삭제() {
		logger.info("#####리뷰 삭제######");
		reviewBO.deleteReview(24);
	}

}
