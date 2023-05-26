package com.PersonalProject.favorite.bo;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
@SpringBootTest
class FavoriteBOTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	FavoriteBO favoriteBO;
	
	@Transactional
	//@Test
	void 관심상품추가() {
		logger.info("######관싱삼품 추가#######");
		favoriteBO.addFavoriteByUserIdProductId(1, 2);
	}
	
	//@Test
	void 관심상품삭제() {
		logger.info("######관심상품 삭제#########");
		favoriteBO.deleteFavoriteByProductId(1, 1);
	}
	
	//@Test
	void 관심상품뿌리기() {
		logger.info("########관심상품 정보 뿌리기#########");
		favoriteBO.generateFavoriteByUserId(1);
	}
	
	//@Test
	void test() {
		fail("Not yet implemented");
	}
	
	@Test
	void 관심상품제너레이트() {
		logger.info("######제너레이트######");
		favoriteBO.generateFavByUserId(1);
	}
	
	//@Test
	void 관심상품토글() {
		logger.info("#####토글#######");
		favoriteBO.favoriteToggle(1, 1);
	}

}
