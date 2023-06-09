package com.PersonalProject.product.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PersonalProject.post.model.Paging;
import com.PersonalProject.product.dao.ProductMapper;
import com.PersonalProject.product.model.Product;

@Service
public class ProductBO {
	private static final int PAGE_LIMIT = 8; // 한 페이지당 보여줄 글 갯수
	
	private static final int BLOCK_LIMIT = 8; // 
	
	@Autowired
	private ProductMapper productMapper;
	
	// 메인 화면에 뿌릴 신발 리스트(페이징)
	public List<Product> getProductList(int page){
		int pageStart = (page -1) * PAGE_LIMIT;
		Map<String, Integer> pagingParams = new HashMap<>();
		pagingParams.put("start", pageStart);
		pagingParams.put("limit", PAGE_LIMIT);
		
		
		List<Product> productList = productMapper.selectProductList(pageStart, PAGE_LIMIT);
		return productList;
	}
	
	// 페이징 파람
	public Paging pagingParam(int page) {
		 // 전체 글 갯수 조회
       int boardCount = productMapper.productCount();
       
       // 전체 페이지 갯수 계산(10/3=3.33333 => 4)
       int maxPage = (int) (Math.ceil((double) boardCount / PAGE_LIMIT));
       
       // 시작 페이지 값 계산(1, 4, 7, 10, ~~~~)
       int startPage = (((int)(Math.ceil((double) page / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1;
       
       // 끝 페이지 값 계산(3, 6, 9, 12, ~~~~)
       int endPage = startPage + BLOCK_LIMIT - 1;
       
       if (endPage > maxPage) {
           endPage = maxPage;
       }
       Paging paging = new Paging();
       
       paging.setPage(page);
       paging.setMaxPage(maxPage);
       paging.setStartPage(startPage);
       paging.setEndPage(endPage);
       return paging;
	}
	
	public Product getProductByProductId(int productId) {
		return productMapper.selectProductByProductId(productId);
	}
	
	// 브랜드 명 페이징
	public List<Product> getProductListByBrand(String brand, int page){
		int pageStart = (page -1) * PAGE_LIMIT;
		Map<String, Integer> pagingParams = new HashMap<>();
		pagingParams.put("start", pageStart);
		pagingParams.put("limit", PAGE_LIMIT);
		
		List<Product> productListByBrand = productMapper.selectProductListByBrand(brand, pageStart, PAGE_LIMIT);
		return productListByBrand;
	}
	
	// 브랜드 명 페이징
	public Paging pagingParamByBrand(int page, String brand) {
		 // 전체 글 갯수 조회
      int boardCount = productMapper.productCountByBrand(brand);
      
      // 전체 페이지 갯수 계산(10/3=3.33333 => 4)
      int maxPage = (int) (Math.ceil((double) boardCount / PAGE_LIMIT));
      
      // 시작 페이지 값 계산(1, 4, 7, 10, ~~~~)
      int startPage = (((int)(Math.ceil((double) page / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1;
      
      // 끝 페이지 값 계산(3, 6, 9, 12, ~~~~)
      int endPage = startPage + BLOCK_LIMIT - 1;
      
      if (endPage > maxPage) {
          endPage = maxPage;
      }
      Paging paging = new Paging();
      
      paging.setPage(page);
      paging.setMaxPage(maxPage);
      paging.setStartPage(startPage);
      paging.setEndPage(endPage);
      return paging;
	}
	
	// 어드민 페이지에 뿌려줄 신발 리스트
	public List<Product> adminGetProductList(){
		return productMapper.adminSelectProductList();
	}
	
	// 어드민 페이지에 뿌려줄 신발
	public Product adminGetProductByProductId(int productId) {
		return productMapper.adminSelectProductByProductId(productId);
	}
	
	// 어드민 페이지에서 신발 정보 수정
	public void updateProduct(
			int productId,
			String brand,
			String name,
			String productImagePath
			) {
		productMapper.updateProduct(productId, brand, name, productImagePath);
	}
	
	// 어드민 페이지에서 신발 추가
	public int addProduct(
			String name,
			String brand,
			String productImagePath) {
		return productMapper.addProduct(name, brand, productImagePath);
	}
	
	// 어드민 페이지에서 신발 삭제
	public int deleteProductByProductId(int productId) {
		return productMapper.deleteProductByProductId(productId);
	}
}
