<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<div class="h-100 d-flex justify-content-between align-items-center">
<h1><a href="/admin/admin_main_view">관리자 페이지</a></h1>
	<div>
		<div>
		<c:if test="${not empty adminId}">
			<span>${adminLoginId}님 안녕하세요</span>
			|
			<a href="/admin/sign_out" class="header-info font-weight-bold">로그아웃</a>
		</c:if> 
		<c:if test="${empty adminId}">
			<a href="/admin/login_page_view" class="header-info font-weight-bold">로그인</a>
		</c:if>
	</div>
	</div>
</div>