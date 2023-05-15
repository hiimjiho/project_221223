<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="d-flex justify-content-center">
	<h2 class="detailBrand mt-5">${product.brand}</h2>
</div>
<div class="mt-4 d-flex justify-content-center">
	<h3>${product.name}</h3>
	<button type="button" class="favoriteBtn btn btn-outline-primary mb-3">관심상품 등록</button>
	
</div>

<div class="d-flex justify-content-center">
	<img src="${product.productImagePath}" alt="신발사진" width=560px height=500px>
</div>

<div class="productInfo">
<b class="d-flex justify-content-center">제품 한줄 평</b>
<c:forEach items="${reviewList}" var="review">
	<div class="review-box d-flex justify-content-between">
		<div><span class="font-weight-bold">${userNickname}</span><span class="reviewContent">${review.content}</span></div>
		<c:if test="${review.userId eq userId}">
		<div class="more-btn">
			<a href="#" data-toggle="modal" data-target="#modal" data-review-id="${review.id}" class="reviewDelBtn">
				<img src="https://www.iconninja.com/files/860/824/939/more-icon.png" width="30">
			</a>
		</div>
		</c:if>
	</div>
</c:forEach>
	
</div>
<div class="comment-write d-flex border-top mt-3 justify-content-center">
	<input type="text" class="form-control border-0 mr-2 review-input" placeholder="댓글 달기" id="review" />
	<button type="button" class="comment-btn btn btn-light" id="reviewBtn" data-product-id="${product.id}">게시</button>
</div>

<hr>
	
<h3 class="mt-5"><b>스타일</b></h3>
	
<div class="d-flex mt-4">
	<div class="ml-5">
	<c:forEach items="${styleList}" var="style">
		<img src="${style.shoesImagePath}" alt="스타일 사진" width=250px height=250px>
	</c:forEach>
	</div>
</div>

<button type="button" class="moreStyleBtn btn btn-outline-primary mt-5"><a href="/style/detail_view?productId=${product.id}">자세히 보기</a></button>



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

<!-- Modal -->
<div class="modal fade" id="likeModal">
	<!-- modal-dialog-centered: 모달 창을 수직 가운데 정렬 -->
	<%--modal-sm 작은 모달. --%>
  <div class="modal-dialog modal-dialog-centered modal-sm">
    <div class="modal-content text-center">
    	<div class="py-3 border-bottom">
     		
     	</div>
     	<div class="py-3">
     		<%-- data-dismiss="modal" => 모달창 닫힘 --%>
     		
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
	});
</script>