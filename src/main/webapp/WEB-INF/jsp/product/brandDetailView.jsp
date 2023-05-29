<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="d-flex">
	<c:forEach items="${productBrandList}" var="postList">
		<div class="mt-5 ml-5"><a id="productInfo" href="/product/detail_view?productId=${postList.id}">
			<img src="${postList.productImagePath}" alt="물건사진" width=225px height=250px>
			<p class="font-weight-bold">${postList.brand}</p>
			<p class="small">${postList.name}</p>
			</a>
		</div>
	</c:forEach>
</div>
	<div>
    <c:choose>
        <%-- 현재 페이지가 1페이지면 이전 글자만 보여줌 --%>
        <c:when test="${paging.page<=1}">
            <span> [이전] </span>
        </c:when>
        <%-- 1페이지가 아닌 경우에는 [이전]을 클릭하면 현재 페이지보다 1 작은 페이지 요청 --%>
        <c:otherwise>
            <a href="/product//brand_detail_list_view?brand=${product.brand}page=${paging.page-1}"> [이전] </a>
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
                <a href="/product//brand_detail_list_view?brand=${product.brand}page=${i}"> ${i} </a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:choose>
        <c:when test="${paging.page>=paging.maxPage}">
            <span> [다음] </span>
        </c:when>
        <c:otherwise>
                <a href="/product//brand_detail_list_view?brand=${product.brand}page==${paging.page+1}"> [다음] </a>
        </c:otherwise>
    </c:choose>
</div>