<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

<div class="d-flex justify-content-center postLogo mt-5">${userNickname}님의 프로필 수정</div>

<div class="d-flex justify-content-center mt-5">
<img src="${user.profileImagePath}" onerror=this.src="/static/img/user/empty_profile.png" alt="프로필 사진" height=120px class="profileImg" id="profileImg">
<img id="preview" alt=썸네일 height=120px class="profileThumbnail d-none">
<img src="/static/img/user/empty_profile.png" alt="빈 프로필"  height=120px class="emptyProfile d-none">
</div>

<div class="d-flex justify-content-center my-4">
	<input type="file" id="file" accept=".jpg, .jpeg, .png, .gif" class="profileImgUpdateBtn">
	<button class="profileImgDelBtn btn btn-outline-danger" data-user-id="${user.id}">기본 이미지로 바꾸기</button>
</div>

<div class="d-flex justify-content-center mt-5">
	<div class="membership d-flex mt-3">
		<input type="text" id="nickname" name="nickname" class="form-control col-8" placeholder="수정할 닉네임을 입력하시오" value="${userNickname}">
		<button type="button" class="btn btn-secondary" id="profileNicknameCheckBtn">중복확인</button>
	</div>
</div>
<div class="d-flex justify-content-center mt-3 mr-3">
	<div id="nicknameLengthCheck" class="d-none text-danger">
		닉네임은 2자 이상 입력해주세요.
	</div>
	<div id="nicknameDuplicatedCheck" class="d-none text-danger">
		중복된 닉네임입니다.
	</div>
	<div id="AvailableNickname" class="d-none text-success">
		사용 가능한 닉네임입니다.
	</div>
</div>
<div class="d-flex justify-content-center mt-5 mr-5">
	<button type="button" class="btn btn-outline-primary btn-lg" id="userProfileUpdateBtn" data-user-id="${userId}">수정 완료</button>
</div>

<script>
	$(document).ready(function(){
		
	/*	$(".profileImgDelBtn").on("click", function(e){
			e.preventDefault();
			$("#profileImg").val("");
			$("#file").val("");
			$("#preview").addClass("d-none");
			$(".profileImg").removeClass("d-none");
			let userId = $(this).data("user-id");
			$.ajax({
				type : "PUT"
				, url : "/user/profile_img_delete"
				, data : {"userId" : userId}
				, success : function(data){
					if(data.code == 1){
						location.reload();
					}
				}
				, error : function(request, status, error){
					alert("관리자에게 문의해주세요");
				}
			});
			
		}); */
		
		$(".profileImgDelBtn").on("click", function(e){
			$(".emptyProfile").removeClass("d-none");
			$("#preview").addClass("d-none");			
			$(".profileImg").addClass("d-none");
		});
		
		 $("#file").on("change", function(event) {
				$("#preview").removeClass("d-none");
				$(".profileImg").addClass("d-none");
			    var file = event.target.files[0];

			    var reader = new FileReader(); 
			    reader.onload = function(e) {
			        $("#preview").attr("src", e.target.result);
			        $(".emptyProfile").addClass("d-none");
			    }
			    reader.readAsDataURL(file);
			});
		
		$("#profileNicknameCheckBtn").on("click", function(){
			
			$("#nicknameLengthCheck").addClass("d-none");
			$("#nicknameDuplicatedCheck").addClass("d-none");
			$("#AvailableNickname").addClass("d-none");
			
			let nickname = $("#nickname").val().trim();
			//alert(nickname);
			
			if(nickname.length < 2){
				$("#nicknameLengthCheck").removeClass("d-none");
				return;
			}
			
			$.ajax({
				//request
				url:"/user/is_duplicated_nickname"
				, data:{"nickname":nickname}
				// response
				, success:function(data){
					if(data.result){
						$("#nicknameDuplicatedCheck").removeClass("d-none");
					}else{
						$("#AvailableNickname").removeClass("d-none");
					}
				}
			});
		});
		
		$("#userProfileUpdateBtn").on("click",function(){
			let nickname = $("#nickname").val().trim();
			let file = $("#file").val();
			let userId = $(this).data("user-id");
			console.log(file);
			if(file != ""){
				//alert(userId);
				if(!nickname){
					alert("닉네임을 입력해주세요");
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
				formData.append("nickname", nickname);
				formData.append("userId", userId);
				formData.append("file", $("#file")[0].files[0]);
				console.log(formData);
				console.log($("#file")[0].files[0]);
				$.ajax({
					type : "put"
					, url : "/user/user_update"
					, data : formData
					, enctype : "multipart/form-data"
					, processData:false	
					, contentType:false
					
					, success:function(data){
						if(data.code == 1){
							alert("프로필 수정이 완료되었습니다");
							location.href="/profile/profile_view?userId=" + userId;
						}
					}
					, error:function(request, status, error){
						alert("다시 시도해주세요");
					}
				});
				
			}else{
				$.ajax({
					type : "PUT"
					, url : "/user/profile_img_delete"
					, data : {"userId" : userId}
					, success : function(data){
						if(data.code == 1){
							
							alert("프로필 수정이 완료되었습니다");
							location.href="/profile/profile_view?userId=" + userId;
						}
					}
					, error : function(request, status, error){
						alert("관리자에게 문의해주세요");
					}
				});
			}

		});
		
	});
</script>