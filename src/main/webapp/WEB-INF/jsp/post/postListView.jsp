<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  

<div class="d-flex justify-content-center">
	<h2 class="mt-3 postLogo">게시판</h2>
</div>
<div class="d-flex justify-content-end">
	<button type="button" class="btn btn-outline-dark mb-2"><a href="/post/create_view">글 쓰기</a></button>
</div>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>NO</th>
				<th>제목</th>
				<th>닉네임</th>
				<th>시간</th>
			</tr>
		</thead>
		<c:forEach items="${postList}" var="postList">
		<tbody>
			<tr>
				<td>${postList.post.id}</td>
				<td width=40%><a href="/post/detail_view?postId=${postList.post.id}" class="postDetailLink">${postList.post.subject}</a></td>
				<td><a href="/profile/profile_view?userId=${postList.user.id}">${postList.user.nickname}</a></td>
				<td>
					<fmt:formatDate value="${postList.post.createdAt}" pattern="yyyy-MM-dd hh:mm:ss"/>
				</td>
			</tr>
		</tbody>
		</c:forEach>
	</table>
	<div>
    <c:choose>
        <%-- 현재 페이지가 1페이지면 이전 글자만 보여줌 --%>
        <c:when test="${paging.page<=1}">
            <span> [이전] </span>
        </c:when>
        <%-- 1페이지가 아닌 경우에는 [이전]을 클릭하면 현재 페이지보다 1 작은 페이지 요청 --%>
        <c:otherwise>
            <a href="/post/list_view?page=${paging.page-1}"> [이전] </a>
        </c:otherwise>
    </c:choose>

    <%--  for(int i=startPage; i<=endPage; i++)      --%>
    <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
        <c:choose>
            <%-- 요청한 페이지에 있는 경우 현재 페이지 번호는 텍스트만 보이게 --%>
            <c:when test="${i eq paging.page}">
                <span><b> ${i} </b></span>
            </c:when>

            <c:otherwise>
                <a href="/post/list_view?page=${i}"> ${i} </a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:choose>
        <c:when test="${paging.page>=paging.maxPage}">
            <span> [다음] </span>
        </c:when>
        <c:otherwise>
                <a href="/post/list_view?page=${paging.page+1}"> [다음] </a>
        </c:otherwise>
    </c:choose>
</div>