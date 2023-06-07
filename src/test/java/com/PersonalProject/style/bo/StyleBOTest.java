package com.PersonalProject.style.bo;

import static org.junit.jupiter.api.Assertions.fail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
class StyleBOTest {

	@Autowired
	StyleBO styleBO;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//@Test
	void 글삭제() {
		logger.info("##### 글 삭제");
		styleBO.deleteStyleByStyleIdUserId(1, 1);
	}
	
	//@Test
	void 글뿌리기() {
		logger.info("###### 데이터 뿌리기#####");
		styleBO.generateStyleCard(1);
	}
	
	@Transactional
	//@Test
	void 글쓰기() {
		logger.info("#######글쓰기");
		styleBO.addStyle(10, "d", 6, null, null);
	}
	
	//@Test
	void test() {
		fail("Not yet implemented");
	}

}
