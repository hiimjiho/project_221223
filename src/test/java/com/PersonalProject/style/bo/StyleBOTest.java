package com.PersonalProject.style.bo;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class StyleBOTest {

	@Autowired
	StyleBO styleBO;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	void 글삭제() {
		logger.info("##### 글 삭제");
		styleBO.deleteStyleByStyleIdUserId(1, 1);
	}
	
	//@Test
	void test() {
		fail("Not yet implemented");
	}

}
