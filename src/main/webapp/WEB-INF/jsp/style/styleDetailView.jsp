<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
    
<h3 class="mt-4"><b>${product.name}</b></h3>
<div class="d-flex justify-content-center">
	<div class="contents-box">
		<%-- 글쓰기 영역: 로그인 된 상태에서만 보여짐 --%>
		<c:if test="${not empty userId}">
		<div class="write-box border rounded m-3">
			<textarea id="writeTextArea" placeholder="사진과 내용을 입력해주세요" class="w-100 border-0"></textarea>
				
			<%-- 이미지 업로드를 위한 아이콘과 업로드 버튼을 한 행에 멀리 떨어뜨리기 위한 div --%>
			<div class="d-flex justify-content-between">
				<div class="file-upload d-flex">
					<%-- file 태그는 숨겨두고 이미지를 클릭하면 file 태그를 클릭한 것처럼 이벤트를 줄 것이다. --%>
					<input type="file" id="file" class="d-none" accept=".gif, .jpg, .png, .jpeg">
					<%-- 이미지에 마우스 올리면 마우스커서가 링크 커서가 변하도록 a 태그 사용 --%>
					<a href="#" id="fileUploadBtn"><img width="35" src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png"></a>
					
					<%-- 업로드 된 임시 파일 이름 저장될 곳 --%>
					<div id="fileName" class="ml-2">
					</div>
				</div>
				<button type="button" class="favoriteBtn btn btn-outline-primary">게시</button>
			</div>
		</div>
		</c:if>
		
		<c:forEach items="${styleList}" var="style">
		<div class="styleContent mt-3">
			<b class="styleWriter">닉네임</b>
			<div class="ml-4">
				<img src="${style.shoesImagePath}" alt="스타일 사진" width=600px height=400px>
			</div>
			<div class="ml-3">
				<img src="/static/img/style/like-button.png" alt="좋아요" width=30px width=30px><b class="ml-2 mt-3">좋아요 5개</b>
			</div>
			<div class="d-flex ml-3">
				<b class="styleWriter mt-2">닉네임</b>
				<span class="ml-2 mt-2">${style.content}</span>
			</div>
			<hr>
			<div class="card-comment m-1 ml-2">
						<span class="font-weight-bold">닉네임</span>
						<span>신발 이쁘네요~~</span>
						<a href="#" class="commentDelBtn" data-comment-id="${commentView.comment.id}">
							<img src="https://www.iconninja.com/files/603/22/506/x-icon.png" width="10px" height="10px">
						</a>
			</div>
			<c:if test="${not empty userId}">
					<div class="comment-write d-flex border-top mb-2 ml-2">
						<input type="text" class="form-control border-0 mr-2 comment-input" placeholder="댓글 달기" id="comment"/> 
						<button type="button" class="comment-btn btn btn-light">게시</button>
					</div>
			</c:if>
		</div>
		</c:forEach>
	</div>
</div>