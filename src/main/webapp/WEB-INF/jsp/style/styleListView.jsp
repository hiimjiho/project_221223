<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

<c:forEach items="${styleList}" var="styleList">
	<div class="d-flex justify-content-center">
		<div class="styleContent mt-3">
		<div class="profile-div d-flex justify-content-between">
		<div>
			<a href="/profile/profile_view?userId=${styleList.style.userId}" class="profileSend"><img src="${styleList.user.profileImagePath}" onerror=this.src="/static/img/user/empty_profile.png" alt="프로필 사진" width=60px height=50px class="profileImg"></a>
			<a href="/profile/profile_view?userId=${styleList.style.userId}" class="profileSend">
			<b class="styleWriter">${styleList.user.nickname}</b></a>
		</div>
			<c:if test="${styleList.style.userId eq userId}">
				<a href="#" data-toggle="modal" data-target="#postModal" data-style-id="${styleList.style.id}" class="styleDelBtn">
					<img src="https://www.iconninja.com/files/860/824/939/more-icon.png" width="30">
				</a>
			</c:if>
		</div>
		<hr>
			<div class="ml-4">
				<a href="/style/style_detail_view?styleId=${styleList.style.id}"><img src="${styleList.style.shoesImagePath}" alt="스타일 사진" width=600px height=400px></a>
			</div>
			<%--좋아요를 누를때(좋아요가 눌러져 있지 않을 때) --%>
			<c:if test="${styleList.hetherLike eq false}">
			<div class="ml-3">
				<a href="#" class="likeBtn" data-style-id="${styleList.style.id}">
					<img src="/static/img/style/like-empty-button.png" alt="좋아요" width=30px width=30px>
				</a>
			<b class="ml-2 mt-3">좋아요 ${styleList.likeCount}개</b>
			</div>
			</c:if>
			<%--좋아요를 취소할 때(좋아요가 눌러져 있을 때) --%>
			<c:if test="${styleList.hetherLike}">
			<div class="ml-3">
				<a href="#" class="likeBtn" data-style-id="${styleList.style.id}">
					<img src="/static/img/style/like-button.png" alt="좋아요" width=30px width=30px>
				</a>
				<b class="ml-2 mt-3">좋아요 ${styleList.likeCount}개</b>
			</div>
			</c:if>
			
			<div class="d-flex ml-3">
				<a href="/profile/profile_view?userId=${styleList.user.id}"><b class="styleWriter mt-2">${styleList.user.nickname}</b></a>
				<span class="ml-2 mt-2">${styleList.style.content}</span>
			</div>
			<hr>
			<c:forEach items="${styleList.commentList}" var="comment">
				<div class="style-comment d-flex justify-content-between">
				<div class="styleComment">
				<a href="/profile/profile_view?userId=${comment.user.id}"><span class="font-weight-bold">${comment.user.nickname}</a></span>:<span class="styleCommentContent">${comment.styleComment.content}</span></div>
				<div class="stylemore-btn">
				<c:if test="${userId eq comment.user.id}">
					<a href="#" data-toggle="modal" data-target="#modal2" data-comment-id="${comment.styleComment.id}" class="styleCommentDelBtn" id="styleCommentDelBtn">
						<img src="https://www.iconninja.com/files/860/824/939/more-icon.png" width="30">
					</a>
				</c:if>
				</div>
			</div>
			</c:forEach>
			<c:if test="${not empty userId}">
					<div class="comment-write d-flex border-top mb-2 ml-2">
						<input type="text" class="style-comment-text form-control border-0 mr-2 comment-input col-10" placeholder="댓글 달기" id="comment"/> 
						<button type="button" class="style-comment-btn btn btn-light" data-style-id="${styleList.style.id}">게시</button>
					</div>
			</c:if>
		</div>
		</div>
		<hr>
		</c:forEach>

<div>
    <c:choose>
        <%-- 현재 페이지가 1페이지면 이전 글자만 보여줌 --%>
        <c:when test="${paging.page<=1}">
            <span> [이전] </span>
        </c:when>
        <%-- 1페이지가 아닌 경우에는 [이전]을 클릭하면 현재 페이지보다 1 작은 페이지 요청 --%>
        <c:otherwise>
            <a href="/style/detail_view?productId=${product.id}&page=${paging.page-1}"> [이전] </a>
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
                <a href="/style/detail_view?productId=${product.id}&page=${i}"> ${i} </a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:choose>
        <c:when test="${paging.page>=paging.maxPage}">
            <span> [다음] </span>
        </c:when>
        <c:otherwise>
                <a href="/style/detail_view?productId=${product.id}&page=${paging.page+1}"> [다음] </a>
        </c:otherwise>
    </c:choose>
</div>

<!-- commentModal -->
<div class="modal fade" id="modal2">
	<!-- modal-dialog-centered: 모달 창을 수직 가운데 정렬 -->
	<%--modal-sm 작은 모달. --%>
  <div class="modal-dialog modal-dialog-centered modal-sm">
    <div class="modal-content text-center">
    	<div class="py-3 border-bottom">
     		<a href="#" id="deleteCommentBtn">댓글 삭제하기</a>
     	</div>
     	<div class="py-3">
     		<%-- data-dismiss="modal" => 모달창 닫힘 --%>
     		<a href="#" data-dismiss="modal" id="deleteModalBtn">취소하기</a>
     	</div>
    </div>
  </div>
</div>

<!-- postModal -->
<div class="modal fade" id="postModal">
	<!-- modal-dialog-centered: 모달 창을 수직 가운데 정렬 -->
	<%--modal-sm 작은 모달. --%>
  <div class="modal-dialog modal-dialog-centered modal-sm">
    <div class="modal-content text-center">
    	<div class="py-3 border-bottom">
     		<a href="#" id="deletePostBtn">글 삭제하기</a>
     	</div>
     	<div class="py-3">
     		<%-- data-dismiss="modal" => 모달창 닫힘 --%>
     		<a href="#" data-dismiss="modal" id="deleteModalBtn">취소하기</a>
     	</div>
    </div>
  </div>
</div>

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
		// 댓글 작성
		$(".style-comment-btn").on("click", function(e){
			e.preventDefault();
			
			let content = $(this).siblings("#comment").val();
			let styleId = $(this).data("style-id");
			console.log(content);
			console.log(styleId);
			//alert(styleId);
			
			if(!content){
				alert("내용을 입력해주세요");
				return
			}
			
			$.ajax({
				type : "post"
				, url : "/style_comment/create"
				, data: {
					"styleId" : styleId ,
					"content" : content
					}
				, success : function(data){
					if(data.code == 1){
						location.reload();
					}else{
						alert(data.errorMessage);
					}
				}
				, error : function(request, status, error){
					alert("글을 저장하는데 실패했습니다.");
				}
			});
		});
		
		$(".styleCommentDelBtn").on("click", function(e){
			e.preventDefault();
			let commentId = $(this).data("comment-id");
			//alert(commentId);
			
		});
		$("#modal2 #deleteCommentBtn").on("click", function(e){
			e.preventDefault();
			let commentId = $("#styleCommentDelBtn").data("comment-id");
			//console.log(commentId);
			$.ajax({
				type : "delete"
				, url : "/style_comment/delete"
				, data : {
					"id" : commentId
					}
			
				, success : function(data){
					if(data.code == 1){
						location.reload(true);
					}else{
						alert(errorMessage);
					}
				}
				, errror : function(request, status, error){
					alert("댓글 삭제에 실패했습니다");
				}
			});
		});
		
		// 좋아요
		$(".likeBtn").on("click", function(e){
			e.preventDefault();
			
			let styleId = $(this).data("style-id");
			//alert(styleId);
			
			$.ajax({
				url: "/like/" + styleId
				, success:function(data){
					if(data.code == 1){
						location.reload(true);
					}else{
						alert(data.errorMessage);
					}
				}
				, error:function(request, status, error){
					alert("다시 시도해주세요");
				}
			});
		});
		
		// 글 삭제
		$("#postModal #deletePostBtn").on("click", function(e){
			e.preventDefault();
			let styleId = $(".styleDelBtn").data("style-id");
			//alert(styleId);
			
			$.ajax({
				type : "delete"
				, url : "/style/delete"
				, data : {
					"styleId" : styleId
					}
				, success:function(data){
					if(data.code == 1){
						alert("게시글이 삭제되었습니다");
						location.reload(true);
					}else{
						alert(data.errorMessage);
					}
				}
				, error:function(request, status, error){
					alert("다시 시도해주세요");
				}
			});
		});
		
	});
</script>