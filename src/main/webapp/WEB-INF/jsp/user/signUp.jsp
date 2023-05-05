<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form id="signUpForm" method="post" action="/user/sign_Up">
	<h3>회원가입</h3>
	<div class="membership d-flex mt-3">
		<input type="text" id="loginId" name="loginId" class="form-control col-6" placeholder="아이디를 입력하시오">
		<button type="button" id="loginIdCheckBtn" class="btn btn-secondary ml-2">중복확인</button>
	</div>
	<div id="idLengthCheck" class="d-none text-danger">
		ID는 5자 이상 입력해주세요.
	</div>
	<div id="idDuplicatedCheck " class="d-none text-danger">
		중복된 ID입니다.
	</div>
	<div id="AvailableId" class="d-none text-success">
		사용 가능한 ID입니다.
	</div>
	
	<div class="membership d-flex mt-3">
		<input type="text" id="nickname" name="nickname" class="form-control col-6" placeholder="닉네임을 입력하시오">
		<button type="button" class="btn btn-secondary ml-2" id="nicknameCheckBtn">중복확인</button>
	</div>
	
	<div id="nicknameLengthCheck" class="d-none text-danger">
		닉네임은 2자 이상 입력해주세요.
	</div>
	<div id="nicknameDuplicatedCheck " class="d-none text-danger">
		중복된 닉네임입니다.
	</div>
	<div id="AvailableNickname" class="d-none text-success">
		사용 가능한 닉네임입니다.
	</div>
	
	<div class="membership d-flex mt-3">
		<input type="password" id="password" name="password" class="form-control col-6" placeholder="비밀번호를 입력하시오">
	</div>
	
	<div class="membership d-flex mt-4">
		<input type="password" id="passwordConfirm" name="passwordConfirm" class="form-control col-6" placeholder="비밀번호 확인을 입력하시오">
	</div>
	
	<div class="membership d-flex mt-4">
		<input type="text" id="email" name="email" class="form-control col-6" placeholder="이메일을 입력하시오">
	</div>
	
	<button type="submit" id="submitBtn" class="btn btn-info mt-3 col-6">회원가입</button>
</form>

<script>
	$(document).ready(function(){
		$("#loginIdCheckBtn").on("click", function(){
			
			$("#idLengthCheck").addClass("d-none");
			$("#idDuplicatedCheck").addClass("d-none");
			$("#AvailableId").addClass("d-none");
			
			let loginId = $("#loginId").val().trim();
			//alert(loginId);
			if(loginId.length < 5){
				$("#idLengthCheck").removeClass('d-none');
				return;
			}
			
			$.ajax({
				// request
				url:"/user/is_duplicated_id"
				, data: {"loginId":loginId}
				//response
				, success:function(data){
					if(data.result){
						//중복
						$("#nicknameDuplicatedCheck").removeClass("d-none")
					}else{
						$("#AvailableId").removeClass("d-none")
					}
				}
			});
		});
		
		$("#nicknameCheckBtn").on("click", function(){
			
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
		$("#signUpForm").on("submit", function(e){
			e.preventDefault();
			
			let loginId = $("#loginId").val().trim();
			let nickname = $("#nickname").val().trim();
			let password = $("#password").val();
			let passwordConfirm = $("#passwordConfirm").val();
			let email = $("#email").val().trim();
			
			if(!loginId){
				alert("아이디를 입력해주세요");
				return false;
			}
			
			if(!nickname){
				alert("비밀번호를 입력해주세요");
				return false;
			}
			
			if(!password){
				alert("비밀번호를 입력해주세요");
				return false;
			}
			
			if(!passwordConfirm){
				alert("비밀번호 확인을 입력해주세요");
				return false;
			}
			
			if(!email){
				alert("이메일을 입력해주세요");
				return false;
			}
			
			if($("#AvailableId").hasClass("d-none")){
				alert("아이디 중복확인을 해주세요");
				return false;
			}
			
			if($("#AvailableNickname").hasClass("d-none")){
				alert("닉네임 중복확인을 해주세요");
				return false;
			}
			
			let url = $(this).attr("action");
			console.log(url);
			let params = $(this).serialize();	// form 태그에 있는 name 속성 값들로 파라미터 구성
			console.log(params);
			
			
		});
		
	});
</script>
