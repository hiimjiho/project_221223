<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    

		<div class="styleContent mt-3">
		<div class="d-flex justify-content-between">
			<b class="styleWriter">${styleCard.user.nickname}</b>
			<c:if test="${styleCard.style.userId eq userId}">
				<a href="#" data-toggle="modal" data-target="#postModal" data-style-id="${styleCard.style.id}" class="styleDelBtn">
					<img src="https://www.iconninja.com/files/860/824/939/more-icon.png" width="30">
				</a>
			</c:if>
		</div>
			<div class="ml-4">
				<img src="${styleCard.style.shoesImagePath}" alt="스타일 사진" width=600px height=400px>
			</div>
			<%--좋아요를 누를때(좋아요가 눌러져 있지 않을 때) --%>
			<c:if test="${styleCard.hetherLike eq false}">
			<div class="ml-3">
				<a href="#" class="likeBtn" data-style-id="${styleCard.style.id}">
					<img src="/static/img/style/like-empty-button.png" alt="좋아요" width=30px width=30px>
				</a>
			<b class="ml-2 mt-3">좋아요 ${styleCard.likeCount}개</b>
			</div>
			</c:if>
			<%--좋아요를 취소할 때(좋아요가 눌러져 있을 때) --%>
			<c:if test="${styleCard.hetherLike}">
			<div class="ml-3">
				<a href="#" class="likeBtn" data-style-id="${styleCard.style.id}">
					<img src="/static/img/style/like-button.png" alt="좋아요" width=30px width=30px>
				</a>
				<b class="ml-2 mt-3">좋아요 ${styleCard.likeCount}개</b>
			</div>
			</c:if>
			
			<div class="d-flex ml-3">
				<b class="styleWriter mt-2">${styleCard.user.nickname}</b>
				<span class="ml-2 mt-2">${styleCard.style.content}</span>
			</div>
			<hr>
			<c:forEach items="${styleCard.commentList}" var="comment">
				<div class="style-comment d-flex justify-content-between">
				<div class="styleComment"><span class="font-weight-bold">${comment.user.nickname}</span><span class="styleCommentContent">${comment.styleComment.content}</span></div>
				<div class="stylemore-btn">
					<a href="#" data-toggle="modal" data-target="#modal2" data-comment-id="${comment.styleComment.id}" class="styleCommentDelBtn" id="styleCommentDelBtn">
						<img src="https://www.iconninja.com/files/860/824/939/more-icon.png" width="30">
					</a>
				</div>
			</div>
			</c:forEach>
			<c:if test="${not empty userId}">
					<div class="comment-write d-flex border-top mb-2 ml-2">
						<input type="text" class="style-comment-text form-control border-0 mr-2 comment-input" placeholder="댓글 달기" id="comment"/> 
						<button type="button" class="style-comment-btn btn btn-light" data-style-id="${styleCard.style.id}">게시</button>
					</div>
			</c:if>
		</div>