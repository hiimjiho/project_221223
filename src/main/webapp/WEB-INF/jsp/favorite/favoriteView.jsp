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
	 	 		<button class="btn btn-outline-danger btn-lg" id="favoriteDelteBtn" data-product-id="${fav.product.id}">관심상품 삭제</button>
	 	 		</c:if>
	 	 </div>
	 </div>
	 <hr>
	 </c:forEach>
</div>

<div>
    <c:choose>
        <%-- 현재 페이지가 1페이지면 이전 글자만 보여줌 --%>
        <c:when test="${paging.page<=1}">
            <span> [이전] </span>
        </c:when>
        <%-- 1페이지가 아닌 경우에는 [이전]을 클릭하면 현재 페이지보다 1 작은 페이지 요청 --%>
        <c:otherwise>
            <a href="/favorite/favorite_view?userId=${userId}&page=${paging.page-1}"> [이전] </a>
        </c:otherwise>
    </c:choose>

    <%--  for(int i=startPage; i<=endPage; i++)      --%>
    <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
        <c:choose>
            <%-- 요청한 페이지에 있는 경우 현재 페이지 번호는 텍스트만 보이게 --%>
            <c:when test="${i eq paging.page}">
                <span><b> ${i} </b></span>
            </c:when>

            <c:otherwise>
                <a href="/favorite/favorite_view?userId=${userId}&page=${i}"> ${i} </a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:choose>
        <c:when test="${paging.page>=paging.maxPage}">
            <span> [다음] </span>
        </c:when>
        <c:otherwise>
                <a href="/favorite/favorite_view?userId=${userId}&page=${paging.page+1}"> [다음] </a>
        </c:otherwise>
    </c:choose>
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