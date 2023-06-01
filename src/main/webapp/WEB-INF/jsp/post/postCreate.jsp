<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
		<div class="form-group">
			Title
			<input type="text" class="form-control" placeholder="Enter title" id="subject" >
		</div>
		
		<div class="form-group">
			Content
			<textarea class="form-control summernote" rows="5" cols="" id="content"></textarea>
			<img id="preview">
		</div>
	
	<button id="postWriteBtn" class="btn btn-primary">글쓰기</button>
		

<div class="d-flex justify-content-start" id="postListBtn">
		<button type="button" class="btn btn-outline-dark mt-3">글 목록</button>
</div>

<script>	
	$(document).ready(function(){
		
		 $('.summernote').summernote({
			 height:400,
			 lang: "ko-KR",	// 한글 설정
			 callbacks: {	//이미지를 첨부하는 부분
				onImageUpload : function(files) {
				uploadSummernoteImageFile(files[0],this);
				
				}
			 }	
		 });
		 	
		 $(".note-image-input").on("change", function(e) {

			    var file = target.files[0];

			    var reader = new FileReader(); 
			    reader.onload = function(e) {

			        $("#preview").attr("src", e.target.result);
			    }

			    reader.readAsDataURL(file);
			    console.log(file);
			});
		 
		 function uploadSummernoteImageFile(file, editor) {
			 
			 $("#postWriteBtn").on("click", function(){
					// validation
					var subject = $("#subject").val();
					var content = $("#content").val();
					//let file = $("#file").val();
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
					
					//if(file != ""){
						
					//let ext = file.split(".").pop().toLowerCase();
						// pop은 배열의 마지막 부분을 가져온다.
					//	if($.inArray(ext, ['jpg', 'jpeg', 'png', 'gif']) == -1){
					//		alert("이미지 파일만 업로드 할 수 있습니다");
					//	$("#file").val("");
					//	return;
					//	}
				//	}
					
						let formData = new FormData();
						formData.append("subject", subject);
						formData.append("content", content);
						formData.append("file", file);
					
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
								$(editor).summernote('insertImage', data.url);
								 $("#thumbnailPath").append("<option value="+data.url+">"+data.originName+"</option>");
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
		 }
		 
		 $("div.note-editable").on('drop',function(e){
	         for(i=0; i< e.originalEvent.dataTransfer.files.length; i++){
	         	uploadSummernoteImageFile(e.originalEvent.dataTransfer.files[i],$("#summernote")[0]);
	         }
	        e.preventDefault();
	   })
	});

</script>
