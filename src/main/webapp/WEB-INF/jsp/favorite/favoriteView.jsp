<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  

<div class="d-flex justify-content-center mt-3 postLogo">
	관심상품
</div>
<hr>
<div>
	<c:forEach items="${favoriteView}" var="fav">
	<div class="d-flex mt-3"><img src="${fav.product.productImagePath}" alt="제품 사진" width=230px height=230px>
	 	 <div class="favoriteBrand mt-5 ml-5">
	 	 	<b>${fav.product.brand}</b>
	 	 	<div class="favoriteProduct">${fav.product.name}</div>
	 	 </div>
	 	 <div class="favoriteProductBtn justify-content-end">
	 	 		<a href="/product/detail_view?productId=${fav.product.id}" type="button" class="btn btn-outline-primary btn-lg">보러가기</a>
	 	 		<c:if test="${userId eq fav.user.id}">
	 	 		<a href="#" type="button" class="btn btn-outline-danger btn-lg" id="favoriteDelteBtn" data-product-id="${fav.product.id}">관심상품 삭제</a>
	 	 		</c:if>
	 	 </div>
	 </div>
	 <hr>
	 </c:forEach>
</div>

<script>
	$(document).ready(function(){
		$("#favoriteDelteBtn").on("click", function(){
			let productId = $(this).data("product-id");
			alert(productId);
			
			$.ajax({
				type:"delete"
				, url:"/favorite/favorite_delete"
				, data:{"productId":productId}
			
				, success:function(data){
					if(data.code == 1){
						location.reload();
					} else{
						alert(errorMessage);
					} 
				}
				, error:function(request, status, error){
					alert("다시 시도해주세요");
				}
			});
		});
	});
</script>