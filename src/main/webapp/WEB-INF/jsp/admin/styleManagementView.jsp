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
    
    
<c:forEach items="${styleList}" var="style">
	<div class="d-flex mt-5">
		id:<b>${style.id}</b>
		<a href="/style/style_detail_view?styleId=${style.id}"><img src="${style.shoesImagePath}" alt="스타일 사진" height=100px widht=100px></a>
		<div>${style.content}</div>
		<div><button class="adminStyleDeleteBtn btn btn-danger ml-5" data-style-id="${style.id}">삭제</button></div>
	</div>
	<hr>
</c:forEach>

<script>
	$(document).ready(function(){
		$(".adminStyleDeleteBtn").on("click", function(e){
			e.preventDefault();
			let styleId = $(this).data("style-id");
			//alert(styleId);
			
			$.ajax({
				type : "delete"
				, url : "/admin/delete_style"
				, data : {
					"styleId" : styleId
					}
				, success:function(data){
					if(data.code == 1){
						alert("게시글이 삭제되었습니다");
						location.reload(true);
					}else{
						alert(data.errorMessage);
					}
				}
				, error:function(request, status, error){
					alert("다시 시도해주세요");
				}
			});
			
		});
	});
</script>