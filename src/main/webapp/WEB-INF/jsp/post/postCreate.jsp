<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  

<div class="d-flex justify-content-center mt-3">
	<div class="w-50">
		<h2>글 작성</h2>
		<input type="text" class="form-control" name="subject" id="subject" placeholder="제목을 입력해주세요.">
		<textarea rows="15" class="form-control mt-3" id="content" placeholder="내용을 입력해주세요."></textarea>
		<div class="d-flex justify-content-end my-4">
			<input type="file" id="file" accept=".jpg, .jpeg, .png, .gif">
		</div>
	</div>
</div>

<div class="postBtnDiv d-flex justify-content-end">
		<button type="button" class="btn btn-outline-dark mt-3 mr-3" id="postWriteBtn">작성 완료</button>
		<button type="button" class="btn btn-outline-dark mt-3" id="fileBtn">사진 첨부</button>
</div>

<div class="d-flex justify-content-start">
		<button type="button" class="btn btn-outline-dark mt-3" id="postListBtn">글 목록</button>
</div>

<script>
	$(document).ready(function(){
		
		$("#postWriteBtn").on("click", function(){
			// validation
			let subject = $("#subject").val();
			let content = $("#content").val();
			let file = $("#file").val();
			//alert(subject);
			//alert(content);
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
			
			let formData = new FormData();
			formData.append("subject", subject);
			formData.append("content", content);
			formData.append("file", $("#file")[0].files[0]);
			
			$.ajax({
				// request
				type:"POST"
				, url:"/post/create"
				, data:formData
				, enctype:"multipart/form-data"
				, processData:false	
				, contentType:false	
				
				// response
				, success:function(data){
					if (data.code == 1){
						// 성공
						alert("메모가 저장되었습니다.");
						location.href="/post/list_view";
					} else{
						// 실패
						alert(data.errorMessage)
					}
				}
				, error:function(request, status, error){
					alert("글을 저장하는데 실패했습니다.");
				}
			});
		});
	});
</script>
