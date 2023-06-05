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

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  

<table class="table">
	<thead>
		<tr>
			<th>ID</th>
			<th>LoginID</th>
			<th>Nickname</th>
			<th>Email</th>
		</tr>
	<c:forEach items="${userList}" var="user">
	<tbody>
		<tr>
			<td>${user.id}</td>
			<td>${user.loginId}</td>
			<td>${user.nickname}</td>
			<td>${user.email}</td>
			<td><button class="userDeleteBtn btn btn-danger" data-user-id="${user.id}">탈퇴</button></td>
		</tr>
	</c:forEach>
	</tbody>
</table>

<script>
	$(document).ready(function(){
		$(".userDeleteBtn").on("click", function(){
			let userId = $(this).data("user-id");
			//console.log(userId);
			
			
		});
	});
</script>