<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <div>
<b>이름</b>
 <input type="text" name="name" id="name" class="form-control">
 </div>
 
 <div>
 <b>브랜드</b>
 <input type="text" name="brand" id="brand" class="form-control"> 
 </div>
 
 <div>
 <b>이미지 주소</b>
 <input type="text" name="productImagePath" class="form-control" id="productImagePath"> 
 </div>
 
 <button class="productInsertBtn btn btn-info mt-3">등록완료</button>
 
 <script>
 	$(document).ready(function(){
 		$(".productInsertBtn").on("click",function(){
 			let name = $("#name").val();
 			let brand = $("#brand").val().trim();
 			let productImagePath = $("#productImagePath").val().trim();
 			
 			if(!name){
 				alert("제품명을 입력해주세요");
 			}
 			
 			if(!brand){
 				alert("제품의 브랜드를 입력해주세요");
 			}
 			
 			if(!productImagePath){
 				alert("이미지 주소를 입력해주세요");
 			}
 			
 			console.log(name);
 			console.log(brand);
 			console.log(productImagePath);
 			
 			$.ajax({
 				type : "post"
 				, url: "/admin/product_create"
 				, data : {
 					"name" : name
 					, "brand" : brand
 					, "productImagePath" : productImagePath
 				}
 				, success : function(data){
 					if(data.code == 1){
 						alert("제품 등록이 완료되었습니다.");
 						location.href = "/admin/product_management_view"
 					}else{
 						alert("다시 시도해주세요");
 					}
 				}
 				, error : function(request, error, status){
 					alert("관리자에게 문의해주세요");
 				}
 			});
 		});
 	});
 </script>