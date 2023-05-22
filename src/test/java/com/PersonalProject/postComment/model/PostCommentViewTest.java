package com.PersonalProject.postComment.model;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.PersonalProject.postComment.bo.PostCommentBO;
@SpringBootTest
class PostCommentViewTest {
	@Autowired
	private PostCommentBO postCommentBO;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	void 코멘트뿌리기() {
		logger.info("####### 코멘트 뿌리기");
		postCommentBO.generatePostCommentByPostId(1);
	}

	//@Test
	void test() {
		fail("Not yet implemented");
	}

}
