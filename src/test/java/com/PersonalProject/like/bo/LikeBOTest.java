package com.PersonalProject.like.bo;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LikeBOTest {

	@Autowired
	LikeBO likeBO;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	void 좋아요() {
		logger.info("##좋아요#########");
		likeBO.likeToggole(1, 1);
	}
	
	//@Test
	void test() {
		fail("Not yet implemented");
	}

}
