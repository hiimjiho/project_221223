<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    
<div class="d-flex justify-content-center">
	<h2 class="mt-3 postLogo">게시판</h2>
</div>

<div class="post-subject mt-5">
	<h3 class="postDetailSubject">${post.subject}</h3>	
</div>

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
<c:forEach items="${postComment}" var="comment">
	<div class="mt-2 d-flex justify-content-between">
		<div><span>${postComment.content}</span></div>
	</div>
</c:forEach>

<div class="d-flex comment-review mt-5">
	<input type="text" id="comment" class="form-control">
	<button class="btn" data-post-id="${postView.post.id}" id="postCommentBtn">게시</button>
</div>

