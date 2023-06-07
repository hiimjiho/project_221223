package com.PersonalProject.styleComment.bo;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
@SpringBootTest

class StyleCommentBOTest {

	@Autowired
	StyleCommentBO styleCommentBO;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//@Test
	void test() {
		fail("Not yet implemented");
	}
	
	@Transactional 
	//@Test
	void 스타일코멘트뿌리기() {
		logger.info("#######스타일 코멘트 뿌리기");
		styleCommentBO.addStyleComment(6, 42, "G");
	}
	
	@Test
	void 코멘트지우기() {
		logger.info("#########코멘트 지우기########");
		styleCommentBO.deleteCommentByStyleId(15);
	}

}
