<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
    
<div class="d-flex justify-content-center postLogo mt-3">
	<span class="profileNickname">${user.nickname}님의 프로필</span>
</div>
<hr>
<div class="d-flex justify-content-center profileDiv">
	<div class="profileImgDiv">
	<img src="${user.profileImagePath}" onerror=this.src="/static/img/user/empty_profile.png" alt="프로필 사진" height=200px class="profileImg">
	<span class="profileName font-weight-bold ml-2">${user.nickname}</span>
	</div>
	
	<div class="profileBtnDiv">
		<c:if test="${userId eq user.id}">
			<div><a href="/profile/profile_update?userId=${user.id}" type="button" class="btn btn-outline-primary my-2 profileBtn">프로필 수정</a></div>
		</c:if>
		<div>
			<a href="/favorite/favorite_view?userId=${user.id}" type="button" class="btn btn-outline-success my-2 profileBtn">관심상품</a>
		</div>
		<div>
			<div><a type="button" class="btn btn-outline-primary my-2 profileBtn" href="/profile/profile_view?userId=${user.id}">스타일 보기</a></div>
		</div>
		<div>
			<div><a type="button" class="btn btn-outline-primary my-2 profileBtn" href="/profile/profile_post_view?userId=${user.id}">쓴 글 보기</a></div>
		</div>
	</div>
</div>
<hr>

<div class="d-flex justify-content-center mt-3">
	<c:forEach items="${styleList}" var="style">
		<div class="ml-3"><a href="/style/style_detail_view?styleId=${style.id}"><img src="${style.shoesImagePath}" alt="스타일 사진" height=230px width=160px></a></div>
	</c:forEach>
</div>