package com.PersonalProject.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.PersonalProject.product.model.Product;

@Repository
public interface ProductMapper {
	public List<Product> selectProductList(@Param("pageStart") int pageStart, @Param("pageLimit") int pageLimit);

	public Product selectProductByProductId(int productId);

	public List<Product> selectProductListByBrand(@Param("brand") String brand, @Param("pageStart") int pageStart,
			@Param("pageLimit") int pageLimit);

	public int productCount();

	public int productCountByBrand(String brand);

	// 어드민 페이지에 뿌려줄 신발 리스트
	public List<Product> adminSelectProductList();

	// 어드민 페이지에 뿌려줄 신발
	public Product adminSelectProductByProductId(int productId);

	// 어드민 페이지에서 신발 정보 수정
	public void updateProduct(
				@Param("productId")int productId,
				@Param("brand")String brand,
				@Param("name")String name,
				@Param("productImagePath")String productImagePath
				);
	
	// 어드민 페이지에서 신발 추가
	public int addProduct(
			@Param("name")String name,
			@Param("brand")String brand,
			@Param("productImagePath")String productImagePath);
	
	// 어드민 페이지에서 신발 삭제
	public int deleteProductByProductId(int productId);
	
}
