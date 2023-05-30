<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="d-flex justify-content-center">
	<h2 class="mt-5"><a href="/product/brand_detail_list_view?brand=${product.brand}" class="detailBrand">${product.brand}</a></h2>
</div>
<div class="mt-4 d-flex justify-content-center">
	<h3>${product.name}</h3>
	<div class="favoriteChooseBtn">
	
	<c:if test="${favoriteCard.hetherFavorite eq false}">
		<button type="button" class="favoriteAddBtn btn btn-outline-primary mb-3" data-product-id="${product.id}">관심상품 등록</button>
	</c:if>
	
	<c:if test="${favoriteCard.hetherFavorite eq true}">
		<button type="button" class="favoriteDeleteBtn btn btn-outline-danger mb-3" data-product-id="${product.id}">관심상품 삭제</button>
	</c:if>
	</div>
</div>

<div class="d-flex justify-content-center">
	<img src="${product.productImagePath}" alt="신발사진" width=560px height=500px>
</div>

<div class="productInfo">
<b class="d-flex justify-content-center">제품 한줄 평</b>
<c:forEach items="${reviewList}" var="reviewList">
	<div class="review-box d-flex justify-content-between">
		<div><a href="/profile/profile_view?userId=${reviewList.review.userId}"><span class="font-weight-bold">${reviewList.user.nickname}</span></a>:
		<span class="reviewContent">${reviewList.review.content}</span></div>
		<c:if test="${reviewList.review.userId eq userId}">
		<div class="more-btn">
			<a href="#" data-toggle="modal" data-target="#modal" data-review-id="${reviewList.review.id}" class="reviewDelBtn">
				<img src="https://www.iconninja.com/files/860/824/939/more-icon.png" width="30">
			</a>
		</div>
		</c:if>
	</div>
</c:forEach>
	<div>
    <c:choose>
        <%-- 현재 페이지가 1페이지면 이전 글자만 보여줌 --%>
        <c:when test="${paging.page<=1}">
            <span> [이전] </span>
        </c:when>
        <%-- 1페이지가 아닌 경우에는 [이전]을 클릭하면 현재 페이지보다 1 작은 페이지 요청 --%>
        <c:otherwise>
            <a href="/product/detail_view?productId=${product.id}&page=${paging.page-1}"> [이전] </a>
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
                <a href="/product/detail_view?productId=${product.id}&page=${i}"> ${i} </a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:choose>
        <c:when test="${paging.page>=paging.maxPage}">
            <span> [다음] </span>
        </c:when>
        <c:otherwise>
                <a href="/product/detail_view?productId=${product.id}&page=${paging.page+1}"> [다음] </a>
        </c:otherwise>
    </c:choose>
</div>
	
</div>
<div class="review-write d-flex border-top mt-3 justify-content-center">
	<input type="text" class="form-control border-0 mr-2 review-input" placeholder="댓글 달기" id="review" />
	<button type="button" class="comment-btn btn btn-light" id="reviewBtn" data-product-id="${product.id}">게시</button>
</div>

<hr>
	
<h3 class="mt-5"><b>스타일</b></h3>
	
<div class="d-flex mt-4">
	<div class="ml-5">
	<c:forEach items="${styleList}" var="style">
		<a href="/style/style_detail_view?styleId=${style.id}"><img src="${style.shoesImagePath}" alt="스타일 사진" width=250px height=250px class="shoesImg"></a>
	</c:forEach>
	</div>
</div>

<button type="button" class="moreStyleBtn btn btn-outline-primary mt-5" data-product-id="${product.id}"><a href="/style/detail_view?productId=${product.id}">자세히 보기</a></button>



<!-- Modal -->
<div class="modal fade" id="modal">
	<!-- modal-dialog-centered: 모달 창을 수직 가운데 정렬 -->
	<%--modal-sm 작은 모달. --%>
  <div class="modal-dialog modal-dialog-centered modal-sm">
    <div class="modal-content text-center">
    	<div class="py-3 border-bottom">
     		<a href="#" id="deletePostBtn">삭제하기</a>
     	</div>
     	<div class="py-3">
     		<%-- data-dismiss="modal" => 모달창 닫힘 --%>
     		<a href="#" data-dismiss="modal" id="deleteModalBtn">취소하기</a>
     	</div>
    </div>
  </div>
</div>

<script>
	$(document).ready(function(){
		$("#reviewBtn").on("click",function(e){
			e.preventDefault();
			
			let productId = $(this).data("product-id");
			//alert(productId);
			let review = $("#review").val();
			//alert(review);
			if(review == ""){
				alert("한줄평을 입력해주세요");
				return;
			}
			
			$.ajax({
				type: "post"
				, url:"/review/create"
				, data: {
					"productId" : productId,
					"content" : review
				}
				
				, success : function(data){
					if(data.code == 1){
						alert("한줄 평이 입력되었습니다.");
						location.reload();
					} else {
						alert(data.errorMessage);
					}
				}
				, error : function(request, status, error){
					alert("한줄평 입력에 실패하였습니다.");
				}
			});
		});
		
		$(".reviewDelBtn").on("click", function(e){
			e.preventDefault();
			let reviewId = $(this).data("review-id");
			$("#modal").data('review-id', reviewId);	
		});
		
		$("#modal #deletePostBtn").on("click", function(e){
			e.preventDefault();
			let reviewId = $("#modal").data("review-id");
			$.ajax({
				type : "delete"
				, url : "/review/delete"
				, data : {"id" : reviewId}
				
				, success : function(data){
					if(data.code == 1){
						alert("댓글이 삭제되었습니다");
						location.reload();
					} else{
						alert(errorMessage);
					}
				}
				, error : function(request, status, error){
					alert("댓글을 삭제하는데 실패했습니다.");
				}
			});
		});
		
		$(".moreStyleBtn").on("click", function(){
			let productId = $(this).data("product-id");
			//alert(productId);
		});
		
		// 관심상품 추가
		$(".favoriteAddBtn").on("click", function(e){
			e.preventDefault();
			let productId = $(this).data("product-id");
			//alert(productId);
			
			$.ajax({
				type:"post"
				, url:"/favorite/favorite_insert"
				, data:{"productId" : productId}
				
				, success : function(data){
					if(data.code == 1){
						alert("관심상품 등록이 완료되었습니다.");
						location.reload();
					}else{
						alert(errorMessage);
					}
				}
				, error : function(request, status, error){
					alert("다시 시도해주세요");
				}
			});
		});
		
		// 관심상품 삭제
		$(".favoriteDeleteBtn").on("click", function(e){
			e.preventDefault();
			let productId = $(this).data("product-id");
			//alert(productId);
			$.ajax({
				type:"delete"
				, url:"/favorite/favorite_delete"
				, data:{"productId":productId}
			
				, success : function(data){
					if(data.code == 1){
						alert("관심상품이 삭제되었습니다.");
						location.reload();
					}else{
						alert(errorMessage);
					}
				}
				, error:function(status, request, error){
					alert("다시 시도해주세요");
				}
			});
		});
		
	});
</script>