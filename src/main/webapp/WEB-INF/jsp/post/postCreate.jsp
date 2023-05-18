<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  

<div class="d-flex justify-content-center mt-3">
	<div class="w-50">
		<h2>글 작성</h2>
		<input type="text" class="form-control" name="subject" id="subject" placeholder="제목을 입력해주세요.">
		
		<textarea rows="15" class="form-control mt-3" id="content" placeholder="내용을 입력해주세요."></textarea>
	</div>
</div>

<div class="postBtnDiv d-flex justify-content-end">
		<button type="button" class="btn btn-outline-dark mt-3 mr-3" id="postWriteBtn">작성 완료</button>
		<button type="button" class="btn btn-outline-dark mt-3" id="fileBtn">사진 첨부</button>
</div>

<div class="d-flex justify-content-start">
		<button type="button" class="btn btn-outline-dark mt-3" id="postListBtn">글 목록</button>
</div>

<script>
	$(document).ready(function(){
		$()
	});
</script>
