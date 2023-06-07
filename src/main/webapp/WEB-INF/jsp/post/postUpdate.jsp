<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="form-group">
			Title
			<input type="text" class="form-control" placeholder="Enter title" id="subject" value="${post.subject}">
		</div>
		
		<div class="form-group">
			Content
			<textarea class="form-control summernote mt-5" rows="5" cols="" id="content">${post.content}</textarea>
			<img id="preview" alt=썸네일 width=300 height=150 class="d-none">
			<input type="file" id="file" accept=".jpg, .jpeg, .png, .gif">	
		</div>

<div class="d-flex justify-content-start" id="postListBtn">
		<button type="button" class="btn btn-outline-info" id="postWriteBtn" data-post-id="${post.id}">수정 완료</button>
</div>

<div class="d-flex justify-content-start" id="postListBtn">
<a href="/post/list_view"><button type="button" class="btn btn-outline-dark mt-3">글 목록</button></a>
</div>
<script>
	$(document).ready(function(){
		
		 $('.summernote').summernote({
			   toolbar: [
				    ['style', ['style']],
				    ['font', ['bold', 'italic', 'underline', 'clear']],
				    ['fontname', ['fontname']],
				    ['color', ['color']],
				    ['para', ['ul', 'ol', 'paragraph']],
				    ['height', ['height']],
				    ['table', ['table']],
				    ['insert', ['link', 'hr']],
				    ['view', ['fullscreen','codeview']],
				    ['help', ['help']]
				  ]
		 		, height:300
		 });
		 
		 $("#file").on("change", function(event) {

			    var file = event.target.files[0];
			    $("#preview").removeClass("d-none");
			    var reader = new FileReader(); 
			    reader.onload = function(e) {

			        $("#preview").attr("src", e.target.result);
			    }

			    reader.readAsDataURL(file);
			});
		
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
