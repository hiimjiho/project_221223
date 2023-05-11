package com.PersonalProject.product.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.PersonalProject.product.model.Product;

@Repository
public interface ProductMapper {
	public List<Product> selectProductList();
	
	public Product selectProductByProductId(int productId);
	
	public List<Product> selectProductListByBrand(String brand);
}
