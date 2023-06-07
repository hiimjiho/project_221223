<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    

<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Gowun+Batang&family=Poor+Story&display=swap" rel="stylesheet">


    
<div class="d-flex justify-content-center postLogo mt-3">
	<span class="profileNickname ml-5">${user.nickname}님의 프로필</span>
</div>

<div class="d-flex justify-content-center profileDiv">
	<div>
	<img src="${user.profileImagePath}" onerror=this.src="/static/img/user/empty_profile.png" alt="프로필 사진" height=120px class="profileImg">
	<span class="profileName font-weight-bold ml-3">${user.nickname}</span>
	</div>
	<c:if test="${userId eq user.id}">
	<div><a href="/profile/profile_update?userId=${user.id}" type="button" class="btn btn-outline-primary" id="profileUpdateBtn">프로필 수정</a></div>
	</c:if>
</div>
<div class="d-flex justify-content-start">
	<a href="/favorite/favorite_view?userId=${user.id}" type="button" class="btn btn-outline-success btn-lg">관심상품</a>
</div>

<div class="profileBtnBox d-flex justify-content-center">
	<div><a type="button" class="btn btn-outline-primary col-6" id="profileUpdateBtn" href="/profile/profile_style_view?userId=${user.id}">스타일 보기</a></div>
	<div><a type="button" class="btn btn-outline-primary col-6" id="profileUpdateBtn" href="/profile/profile_post_view?userId=${user.id}">쓴 글 보기</a></div>
</div>