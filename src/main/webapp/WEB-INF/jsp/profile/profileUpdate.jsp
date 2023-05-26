<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

<div class="d-flex justify-content-center postLogo mt-5">${userNickname}님의 프로필 수정</div>

<div class="d-flex justify-content-center mt-5">
<img src="${user.profileImagePath}" onerror=this.src="/static/img/user/empty_profile.png" alt="프로필 사진" height=120px class="profileImg">
</div>

<div class="d-flex justify-content-center my-4">
	<input type="file" id="file" accept=".jpg, .jpeg, .png, .gif" class="profileImgUpdateBtn">
	<button class="btn btn-outline-danger">파일 삭제</button>
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
			alert(userId);
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
			
			$.ajax({
				url:"/user/update"
				, type:"put"
				, data:formData
				, enctype:"multipart/form-data"
				, processData:false	
				, contentType:false
				
				, success:function(data){
					if(data.code == 1){
						location.href="/profile/profile_view?userId=" + userId;
					}
				}
				, error:function(request, status, error){
					alert("다시 시도해주세요");
				}
			});
		});
		
	});
</script>