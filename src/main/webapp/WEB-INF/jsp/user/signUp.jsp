<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form id="signUpForm" method="post" action="/user/sign_Up">
	<h3>회원가입</h3>
	<div class="membership d-flex mt-3">
		<input type="text" id="loginId" name="loginId" class="form-control col-6" placeholder="아이디를 입력하시오">
		<button type="button" id="loginIdCheckBtn" class="btn btn-secondary ml-2">중복확인</button>
	</div>
	
	<div class="membership d-flex mt-5">
		<input type="text" id="nickname" name="nickname" class="form-control col-6" placeholder="닉네임을 입력하시오">
		<button type="button" class="btn btn-secondary ml-2" id="nicknameCheckBtn">중복확인</button>
	</div>
	
	<div class="membership d-flex mt-5">
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