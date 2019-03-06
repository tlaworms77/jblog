<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="navigation">
	<h2>카테고리</h2>
	<ul>
		<c:choose>
			<c:when test='${ param.menu == "main" }'>
				<c:if test="${!empty postlist }">
					<c:forEach items="${categorylist}" var="category">
						<li><a href="${ pageContext.servletContext.contextPath }/${id}/${category.no}">${category.name}(${category.postAmount})</a></li>
					</c:forEach>
				</c:if>
			</c:when>
			<c:otherwise>
				<c:if test="${!empty postlist }">
					<c:forEach items="${categorylist}" var="category">
						<li><a href="${ pageContext.servletContext.contextPath }/${id}/${category.no}">${category.name}</a></li>
					</c:forEach>
				</c:if>
			</c:otherwise>
		</c:choose>
	</ul>
</div>