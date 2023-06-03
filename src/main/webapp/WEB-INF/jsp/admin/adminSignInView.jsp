<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- cdn -->
<script src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>
<!-- css -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<!-- bundle slim 제거 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
<!-- 내가 만든 스타일시트 -->
<link rel="stylesheet" type="text/css" href="/static/css/style.css">

   
<div class="d-flex justify-content-center">
	<h1>관리자 페이지</h1>
    <form id="adminSignInForm" method="post" action="/admin/admin_sign_in">
	<h2 class="loginLogo">로그인</h2>
	<div class="membership d-flex mt-5">
		<input type="text" id="adminLoginId" name="adminLoginId" class="form-control" placeholder="아이디를 입력하시오">
	</div>
	
	<div class="membership d-flex mt-4">
		<input type="password" id="adminPassword" name="adminPassword" class="form-control" placeholder="비밀번호를 입력하시오">
	</div>
	<button type="submit" id="submitBtn" class="btn btn-info mt-3 col-6">로그인</button>
</form>
</div>
<script>

	$(document).ready(function(){
		
		$("#submitBtn").on("click", function(e){
			e.preventDefault();
			let adminLoginId = $("#adminLoginId").val().trim();
			let adminPassword = $("#adminPassword").val();
			
			if(!adminLoginId){
				alert("아이디를 입력해주세요");
				return false;
			}
			
			if(!adminPassword){
				alert("비밀번호를 입력해주세요");
				return false;
			}
			
			let url = $("#adminSignInForm").attr("action");
			console.log(url);
			let params = $("#adminSignInForm").serialize();
			console.log(params);
			
			$.post(url, params)
			.done(function(data){
				if(data.result == "success") {
					 location.href = "/admin/admin_main_view"; 
				} else{
					alert(data.errorMessage);
				}
			});
			
		});
	});
</script>