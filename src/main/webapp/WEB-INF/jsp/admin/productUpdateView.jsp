<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div>
<b>이름</b>
 <input type="text" name="name" id="name" class="form-control" value="${product.name}">
 </div>
 
 <div>
 <b>브랜드</b>
 <select name="brand" id="brand" class="form-control">
            <option>NIKE</option>
            <option>ADIDAS</option>
            <option>NEW BALANCE</option>
            <option>CONVERSE</option>
            <option>ASICS</option>
            <option>VANS</option>
</select>
 </div>
 
 <div>
 <b>이미지 주소</b>
 
 <img src="${product.productImagePath}" alt="제품 사진" width=560px height=500px> 
 <input type="text" name="productImagePath" class="form-control" id="productImagePath" value="${product.productImagePath}"> 
 </div>
 
 <button class="productUpdateBtn btn btn-info" data-product-id="${product.id}">수정완료</button>
 
 <script>
 	$(document).ready(function(){
 		$(".productUpdateBtn").on("click",function(e){
 			e.preventDefault();
 			
 			let productId = $(this).data("product-id");
 			let name = $("#name").val();
 			let brand = $("#brand").val().trim();
 			let productImagePath = $("#productImagePath").val().trim();
 			console.log(productId);
 			console.log(name);
 			console.log(brand);
 			console.log(productImagePath);
 			
 			$.ajax({
 				type : "put"
 				, url : "/admin/update_product"
 				, data : {
 					"productId" : productId
 					, "name" : name
 					, "brand" : brand
 					, "productImagePath" : productImagePath
 				}
 				, success : function(data){
 					if(data.code == 1){
 						alert("수정이 완료되었습니다,");
 						location.href="/admin/product_management_view";
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