<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<div class="d-flex justify-content-center mr-5">
    <form id="adminSignInForm" method="post" action="/admin/admin_sign_in">
	<h2 class="loginLogo">로그인</h2>
	<div class="membership d-flex mt-5 mr-5">
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