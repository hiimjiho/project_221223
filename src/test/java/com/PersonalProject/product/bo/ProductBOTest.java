package com.PersonalProject.product.bo;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class ProductBOTest {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductBO productBO;

	//@Test
	void test() {
		fail("Not yet implemented");
	}
	
	//@Test
	void 신발추가() {
		logger.info("#########신발 추가####");
		productBO.addProduct("SUPERSTAR 82 CBLACK/ALUMIN/CWHITE", "ADIDAS", "https://image.msscdn.net/images/goods_img/20220509/2545799/2545799_1_500.jpg");
	}
	
	//@Test 
	void 신발삭제(){
		logger.info("#######신발 삭제##########");
		productBO.deleteProductByProductId(15);
	}
	
	@Test
	void 신발업데이트() {
		logger.info("##########신발 업데이트########");
		productBO.updateProduct(23, "ADIDAS", "ADIDAS", "https://image.msscdn.net/images/goods_img/20220509/2545799/2545799_1_500.jpg");
	}

}
