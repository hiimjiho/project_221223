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
	<button type="button" class="btn btn-outline-warning" id="updatePostBtn">글 수정</button>
</div>
</c:if>

<hr>
<div class="post-content d-flex mt-5">
	${postView.post.content}
</div>
<hr>

<b class="postCmn">댓글</b>
<div class="comment-review mt-5">
	<b>닉네임</b> 내용
</div>

<script>
	$(document).ready(function(){
		$("#deletePostBtn").on("click", function(){
			let postId = $(this).data("post-id");
			//alert(postId);
			
			
		});
		
		
	});
</script>