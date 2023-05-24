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
		
		<c:forEach items="${styleCardList}" var="styleCard">
		<div class="styleContent mt-3">
		<div class="d-flex justify-content-between">
			<a href="/profile/profile_view?userId=${styleCard.user.id}" class="profileSend"><b class="styleWriter">${styleCard.user.nickname}</b></a>
			<c:if test="${styleCard.style.userId eq userId}">
				<a href="#" data-toggle="modal" data-target="#postModal" data-style-id="${styleCard.style.id}" class="styleDelBtn">
					<img src="https://www.iconninja.com/files/860/824/939/more-icon.png" width="30">
				</a>
			</c:if>
		</div>
			<div class="ml-4">
				<a href="/style/style_detail_view?styleId=${styleCard.style.id}"><img src="${styleCard.style.shoesImagePath}" alt="스타일 사진" width=600px height=400px></a>
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
		</c:forEach>
	</div>
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
			formData.append("productId", productId);
			formData.append("content", content);
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
						location.reload(true);
					} else {
						alert(data.errorMessage);
					}
				},
				error : function(request, status, error) {
					alert("글을 저장하는데 실패했습니다.");
				}
			});
		});
		
		// 댓글 작성
		$(".style-comment-btn").on("click", function(e){
			e.preventDefault();
			
			let content = $(this).siblings("#comment").val();
			let styleId = $(this).data("style-id");
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
		
		// 댓글 삭제
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