<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center mt-3">
	<div class="w-50">
		<h2>글 수정</h2>
		<input type="text" class="form-control" name="subject" id="subject" placeholder="제목을 입력해주세요." value="${post.subject}">
		<textarea rows="15" class="form-control mt-3" id="content" placeholder="내용을 입력해주세요.">${post.content}</textarea>
		<div class="d-flex justify-content-end my-4">
			<input type="file" id="file" accept=".jpg, .jpeg, .png, .gif">
		</div>
		<div class="uploadImage">
			<c:if test="${not empty post.imagePath}">
				<img class="fu-img-box" src="${post.imagePath}" alt="업로드 된 이미지" width="200">
			</c:if>	
		</div>
	</div>
</div>

<div class="postBtnDiv d-flex justify-content-end">
		<button type="button" class="btn btn-outline-dark mt-3 mr-3" id="postWriteBtn" data-post-id="${post.id}">수정 완료</button>
		<button type="button" class="btn btn-outline-dark mt-3" id="fileBtn">사진 첨부</button>
</div>

<div class="d-flex justify-content-start mb-5">
		<button type="button" class="postListBtn btn btn-outline-dark">글 목록</button>
</div>

<script>
	$(document).ready(function(){
		
		// 수정버튼
		$("#postWriteBtn").on("click", function(){
			//validation
			let subject = $("#subject").val().trim();
			let content = $("#content").val();
			let file = $("#file").val();
			let postId = $(this).data("post-id");
			console.log(subject);
			console.log(content);
			console.log(file);
			console.log(postId);
			
			if(!subject){
				alert("제목을 입력해주세요");
				return;
			}
			
			if(!content){
				alert("내용을 입력해주세요");
				return;
			}
			if(file != ""){
				let ext = file.split(".").pop().toLowerCase();
				// pop은 배열의 마지막 부분을 가져온다.
				if($.inArray(ext, ['jpg', 'jpeg', 'png', 'gif']) == -1){
					alert("이미지 파일만 업로드 할 수 있습니다");
					$("#file").val("");
					return;
				}
			}
			// 이미지 업로드를 위해 폼 데이터 만들기
			let formData = new FormData();
			formData.append("postId", postId);
			formData.append("subject", subject);
			formData.append("content", content);
			formData.append("file", $("#file")[0].files[0]);
			console.log(formData);
			
			
			$.ajax({
				type:"put"
				, url:"/post/update"
				, data:formData
				, enctype:"multipart/form-data"
				, processData:false	
				, contentType:false
				
				, success:function(data){
					if(data.code == 1){
						alert("글이 수정되었습니다");
						location.href="/post/detail_view?postId=" + postId;
					}
				}
				, error:function(request, status, error){
					alert("글수정이 실패했습니다 다시 시도해주세요");
				}
			});
			
		});
	});
</script>
