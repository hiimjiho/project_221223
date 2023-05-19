<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  

<div class="d-flex justify-content-center">
	<b><h3 class="mt-4">게시판</h3></b>
</div>
<div class="d-flex justify-content-end">
	<button type="button" class="btn btn-outline-dark mb-2"><a href="/post/create_view">글 쓰기</a></button>
</div>
	<table class="table">
		<thead>
			<tr>
				<th>NO</th>
				<th>제목</th>
				<th>닉네임</th>
				<th>시간</th>
			</tr>
		</thead>
		<c:forEach items="${postList}" var="post">
		<tbody>
			<tr>
				<td>${post.id}</td>
				<td><a href="/post/detail_view?postId=${post.id}">${post.subject}</a></td>
				<td>${post.userId}</td>
				<td>${post.createdAt}</td>
			</tr>
		</tbody>
		</c:forEach>
	</table>
	