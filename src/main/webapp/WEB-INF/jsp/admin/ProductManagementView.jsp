<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  


<div class="d-flex justify-content-end">
	<a href="/admin/product_create_view" class="btn btn-success mb-3">상품 추가하기</a>
</div>

<table class="table">
	<thead>
		<tr>
			<th>사진</th>
			<th>ID</th>
			<th>브랜드</th>
			<th>이름</th>
			<th>관리</th>
		</tr>
	</thead>
	<c:forEach items="${productList}" var="product">
	<tbody>
		<tr>
			<td><img src="${product.productImagePath}" alt="상품 사진" width=100px height=100px></td>
			<td>${product.id}</td>
			<td>${product.brand}</td>
			<td>${product.name}</td>
			<td>
			<a href="/admin/product_update_view?productId=${product.id}" class="btn btn-info">수정</a>
			<button class="productDeleteBtn btn btn-danger" data-product-id="${product.id}">삭제</button>
			<a href="/product/detail_view?productId=${product.id}" class="btn btn-secondary">보러가기</a>
			</td>
		</tr>
	</tbody>
	</c:forEach>
</table>

<script>
	$(document).ready(function(){
		$(".productDeleteBtn").on("click", function(){
			let productId = $(this).data("product-id");
			
			
			$,ajax({
				type : "delete"
				, url : "/admin/product_delete"
				, data : {"productId" : productId}
				, success : function(data){
					if(data.code == 1){
						alert("신발이 삭제되었습니다.");
						location.reload();
					}else{
						alert("다시 시도해주세요");
					}
				}
				, error : function(request, status, error){
					alert("관리자에게 문의해주세요");
				}
			});
		});
	});
</script>