<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
    
<h3 class="mt-4"><a id="productInfo" href="/product/detail_view?productId=${product.id}"><b>${product.name}</b></a></h3>
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
				<button type="button" class="writeBtn btn btn-outline-primary" data-product-id="${product.id}">게시</button>
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

<script>
	$(document).ready(function(){
		$("#fileUploadBtn").on("click", function(e) {
			e.preventDefault(); 
			$("#file").click(); 
		});
		
		$("#file").on('change', function(e) {
			let fileName = e.target.files[0].name; //예	secured.jpg
			console.log(fileName);

			// 확장자 유효성 확인
			let ext = fileName.split(".").pop().toLowerCase();
			if (ext != "jpg" && ext != "png" && ext != "jpeg" && ext != "gif") {
				alert("이미지 파일만 업로드할 수 있습니다.");
				$("#file").val(""); // 파일태그의 파일 제거.
				$("#fileName").text(""); // 파일 이름 비우기
				return;
			}

			// 유효성 통과한 이미지는 상자에 업로드 된 파이 이름 노출
			$("#fileName").text(fileName);
		});
		
		$(".writeBtn").on("click", function(){
			// validation
			let content = $("#writeTextArea").val().trim()
			let file = $("#file").val();
			let productId = $(this).data("product-id");
			
			if (!content) {
				alert("내용을 입력해주세요");
				return;
			}

			if (!file) {
				alert("파일을 첨부해주세요");
				return;
			}

			// 글내용, 이미지, 확장자 체크

			let ext = file.split(".").pop().toLowerCase();
			if (ext != "jpg" && ext != "png" && ext != "jpeg" && ext != "gif") {
				alert("이미지 파일만 업로드할 수 있습니다.");
				$("#file").val(""); // 파일태그의 파일 제거.
				return;
			}

			let formData = new FormData();
			formData.append("content", content);
			formData.append("productId", productId);
			formData.append("file", $("#file")[0].files[0]);
			
			$.ajax({
				// response
				type : "POST",
				url : "/style/create",
				data : formData,
				enctype : "multipart/form-data" // file 업로드를 위한 필수 설정
				,
				processData : false // file 업로드를 위한 필수 설정
				,
				contentType : false // file 업로드를 위한 필수 설정

				// response
				,
				success : function(data) {
					if (data.code == 1) {
						alert("게시글 작성이 완료되었습니다");
						location.href = "/style/detail_view";
					} else {
						alert(data.errorMessage);
					}
				},
				error : function(request, status, error) {
					alert("글을 저장하는데 실패했습니다.");
				}
			});
		});
		
	
	});
</script>