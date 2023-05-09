<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="d-flex">
	<c:forEach items="${productList}" var="postList">
		<div class="mt-5 ml-5"><a id="productInfo" href="/product/product_detail_view?productId=${post.id}">
			<img src="${postList.productImagePath}" alt="물건사진" width=250px height=250px>
			<p class="font-weight-bold">${postList.brand}</p>
			<p class="small">${postList.name}</p>
			</a>
		</div>
	</c:forEach>
</div>