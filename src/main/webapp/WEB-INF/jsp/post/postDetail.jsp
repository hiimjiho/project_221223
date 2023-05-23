<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    
<div class="d-flex justify-content-center">
	<h2 class="mt-3 postLogo">게시판</h2>
</div>

<div class="post-subject mt-5">
	<h3 class="postDetailSubject">${postView.post.subject}</h3>	
	작성자: <b>${postView.user.nickname}</b>
</div>

<c:if test="${postView.post.userId eq userId}">
<div class="d-flex justify-content-end">
	<button type="button" class="btn btn-outline-danger mr-2" id="deletePostBtn" data-post-id="${postView.post.id}">글 삭제</button>
	<a class="btn btn-outline-info" href="/post/update_view?postId=${postView.post.id}">수정하기</a>
</div>
</c:if>

<c:if test="${not empty postView.post.imagePath}">
<div class="d-flex">
	<img src="${postView.post.imagePath}" alt="포스트 사진" width=300px;>
</div>
</c:if>

<div class="post-content d-flex mt-5">
	${postView.post.content}
</div>
<hr>

<hr>
<b class="postCmn">댓글</b>
<c:forEach items="${postCommentList}" var="comment">
	<div class="mt-2 d-flex justify-content-between">
		<div><span class="font-weight-bold">${comment.user.nickname}</span> <span>${comment.postComment.content}</span></div>
	
	<c:if test="${comment.postComment.userId eq userId}">
		<div class="more-btn">
			<a href="#" data-toggle="modal" data-target="#modal" data-comment-id="${comment.postComment.id}" class="postCommentDelBtn">
				<img src="https://www.iconninja.com/files/860/824/939/more-icon.png" width="30">
			</a>
		</div>
	</c:if>
	</div>
</c:forEach>

<div class="d-flex comment-review mt-5">
	<input type="text" id="comment" class="form-control">
	<button class="btn" data-post-id="${postView.post.id}" id="postCommentBtn">게시</button>
</div>



<!-- Modal -->
<div class="modal fade" id="modal">
	<!-- modal-dialog-centered: 모달 창을 수직 가운데 정렬 -->
	<%--modal-sm 작은 모달. --%>
  <div class="modal-dialog modal-dialog-centered modal-sm">
    <div class="modal-content text-center">
    	<div class="py-3 border-bottom">
     		<a href="#" id="deleteCommentBtn">삭제하기</a>
     	</div>
     	<div class="py-3 border-bottom">
     		<%-- data-dismiss="modal" => 모달창 닫힘 --%>
     		<a href="#" data-dismiss="modal" id="updateModalBtn">수정하기</a>
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
		$("#postCommentBtn").on("click", function(){
			// validation
			let content = $("#comment").val();
			let postId = $(this).data("post-id");
			
			if(!comment){
				alert("내용을 입력해주세요");
			}
			$.ajax({
				type:"post"
				, url:"/post_comment/create"
				, data : {
					"content" : content,
					"postId" : postId}
			
				, success : function(data){
					if(data.code == 1){
						location.reload();
					}else{
						alert(data.errorMessage)
					}
				}
				, error : function(request, status, error){
					alert("입력에 실패하였습니다");
				}
			});
			
		});
		
		$(".postCommentDelBtn").on("click", function(e){
			e.preventDefault();
			let postCommentId = $(this).data("comment-id");
			$("#modal").data('comment-id', postCommentId);
		});
		
		$("#modal #deleteCommentBtn").on("click", function(e){
			e.preventDefault();
			let postCommentId = $("#modal").data("comment-id");
			//alert(postCommentId);
			
			$.ajax({
				type:"delete"
				, url:"/post_comment/delete"
				, data:{"postCommentId":postCommentId}
			
				, success:function(data){
					if(data.code == 1){
						location.reload();
					}else{
						alert(data.errorMessage);
					}
				}
				, error:function(request, status, error){
					alert("댓글을 삭제하는데 실패했습니다");
				}
			});
		});
		$("#deletePostBtn").on("click", function(){
			let postId = $(this).data("post-id");
			alert(postId);
			
			$.ajax({
				type:"delete"
				, url:"/post/delete"
				, data:{"postId":postId}
			
				, success:function(data){
					if(data.code == 1){
						alert("글이 삭제되었습니다.");
						location.href="/post/list_view";
					}else{
						alert(data.errorMessage);
					}
				}
				, error:function(request, status, error){
					alert("글을 삭제하는데 실패했습니다.");
				}
			});
		});
		
		$("#updatePostBtn").on("click", function(){
			type:""
		});
	});
</script>