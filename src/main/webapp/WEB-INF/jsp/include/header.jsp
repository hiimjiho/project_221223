<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!-- 로고 -->
<div class="h-100 d-flex justify-content-between align-items-center">
	<div>
		<h1 class="font-weight-bold"><a class="headerLogo" href="/product/main_view">로고</a></h1>
	</div>
	
	<div>
		<c:if test="${not empty userId}">
			<span>${userNickname}님 안녕하세요</span>
			|
			<a href="/user/sign_out" class="font-weight-bold">로그아웃</a>
			|
			<a href="#">내 정보</a>
		</c:if> 
		<c:if test="${empty userId}">
			<a href="/user/sign_in_view" class="font-weight-bold">로그인</a>
		</c:if>
	</div>
</div>





