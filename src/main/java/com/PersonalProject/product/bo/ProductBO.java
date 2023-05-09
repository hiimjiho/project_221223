package com.PersonalProject.product.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PersonalProject.product.dao.ProductMapper;
import com.PersonalProject.product.model.Product;

@Service
public class ProductBO {
	@Autowired
	private ProductMapper productMapper;
	
	public List<Product> getProductList(){
		return productMapper.selectProductList();
	}
	
	public Product getProductByProductId(int productId) {
		return productMapper.selectProductByProductId(productId);
	}
}
