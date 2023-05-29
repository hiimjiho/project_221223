package com.PersonalProject.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.PersonalProject.product.model.Product;

@Repository
public interface ProductMapper {
	public List<Product> selectProductList(
			@Param("pageStart") int pageStart,
			@Param("pageLimit") int pageLimit);
	
	public Product selectProductByProductId(int productId);
	
	public List<Product> selectProductListByBrand(
			@Param("brand")String brand,
			@Param("pageStart") int pageStart,
			@Param("pageLimit") int pageLimit);
	
	public int productCount();
	
	public int productCountByBrand(String brand);
}
