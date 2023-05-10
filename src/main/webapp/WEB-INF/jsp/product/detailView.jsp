<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div>
	<h2 class="detailBrand mt-5">${product.brand}</h2>
</div>
<div class="mt-4 d-flex">
	<h3>${product.name}</h3>
	<button type="button" class="favoriteBtn btn btn-outline-primary mb-3">관심상품 등록</button>
</div>

<div>
	<img src="${product.productImagePath}" alt="신발사진" width=500px
		height=500px>
</div>

<b>제품 한줄 평</b>
<div class="card-comment m-1">
						<span class="font-weight-bold">닉네임</span>
						<span>내용</span>
					</div>
<div class="comment-write d-flex border-top mt-3">
	<input type="text" class="form-control border-0 mr-2 comment-input" placeholder="댓글 달기" id="comment" />
	<button type="button" class="comment-btn btn btn-light" data-post-id="">게시</button>
</div>
	
	
<h3 class="mt-5"><b>스타일</b></h3>
	
	<div class="contents-box">
		<%-- 글쓰기 영역: 로그인 된 상태에서만 보여짐 --%>
		<div class="write-box border rounded m-3">
			<textarea id="writeTextArea" placeholder="내용을 입력해주세요" class="w-100 border-0"></textarea>
				
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
				<button id="writeBtn" class="btn btn-info">게시</button>
			</div>
		</div>	
</div>
<div class="d-flex">
	<div class="ml-5">
		<span class="styleNickname"><b>${userNickname}</b></span><p>
		<a href="#"><img src="https://cdn.pixabay.com/photo/2016/03/24/00/24/chucks-1275904_960_720.jpg" alt="스타일 사진" width=250px height=250px></a>
	</div>
	
	<div class="ml-5">
		<span class="styleNickname"><b>${userNickname}</b></span><p>
		<img src="https://cdn.pixabay.com/photo/2016/03/24/00/24/chucks-1275904_960_720.jpg" alt="스타일 사진" width=250px height=250px>
	</div>
	
	<div class="ml-5">
		<span class="styleNickname"><b>${userNickname}</b></span><p>
		<img src="https://cdn.pixabay.com/photo/2016/03/24/00/24/chucks-1275904_960_720.jpg" alt="스타일 사진" width=250px height=250px>
	</div>
</div>
	
	



<script>
	
</script>