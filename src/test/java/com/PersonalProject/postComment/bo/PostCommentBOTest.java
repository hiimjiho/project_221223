package com.PersonalProject.postComment.bo;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class PostCommentBOTest {

	@Autowired
	PostCommentBO postCommentBO;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Transactional
	@Test
	void 댓글달기() {
		logger.info("#######댓글 쓰기");
		postCommentBO.addPostCommentByContentPostId(1, 1, "gd");
	}
	
	//@Test
	void test() {
		fail("Not yet implemented");
	}

}
