package com.PersonalProject.post.bo;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PostBOTest {
	
	@Autowired
	PostBO postBO;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//@Test
	//void 게시글삭제() {
		//logger.info("######게시글 삭제######");
		//postBO.deletePostByPostidUserId(5, 2);
	//}
	
	//@Test
	void test() {
		fail("Not yet implemented");
	}

}
