<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  


<table class="table">
	<thead>
		<tr>
			<th>ID</th>
			<th>제목</th>
			<th>관리</th>
		</tr>
	</thead>
	<c:forEach items="${postList}" var="post">
	<tbody>
		<tr>
			<td>${post.id}</td>
			<td>${post.subject}</td>
			<td><button class="adminPostDeleteBtn btn btn-danger" data-post-id="${post.id}">삭제</button></td>
		</tr>
	</tbody>
	</c:forEach>
</table>

<script>
	$(document).ready(function(){
		$(".adminPostDeleteBtn").on("click",function(){
			let postId = $(this).data("post-id");
			//console.log(postId);
			
			$.ajax({
				type : "delete"
				, url : "/admin/post_delete"
				, data : {"postId" : postId}
				, success : function(data){
					if(data.code == 1){
						alert("게시글이 삭제되었습니다");
						location.reload();
					}else{
						alert("다시 시도해주세요");
					}
				}
				, error : function(request, status, error){
					alert("관리자에게 문의해주세요");
				}
			});
		});
	});
</script>