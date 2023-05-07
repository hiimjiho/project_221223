<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form id="signInForm" method="post" action="/user/sign_in">
	<h2>로그인</h2>
	<div class="membership d-flex mt-5">
		<input type="text" id="loginId" name="loginId" class="form-control col-6" placeholder="아이디를 입력하시오">
	</div>
	
	<div class="membership d-flex mt-4">
		<input type="password" id="password" name="password" class="form-control col-6" placeholder="비밀번호를 입력하시오">
	</div>
	<button type="submit" id="submitBtn" class="btn btn-info mt-3 col-6">로그인</button>
</form>

<script>
	$(document).ready(function(){
		let loginId = $("#loginId").val().trim();
		let password = $("#password").val();
	});
</script>