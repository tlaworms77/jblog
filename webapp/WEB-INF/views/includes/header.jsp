<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>
	<ul class="menu">
		<c:choose>
			<c:when test="${ empty authuser }">
				<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
				<li><a href="${pageContext.request.contextPath}/user/join">회원가입</a></li>
			</c:when>
			<c:otherwise>
				<li>${ authuser.name }님 안녕하세요 ^^;</li>
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
				<li><a href="${pageContext.request.contextPath}/${authuser.id}">내블로그</a></li>
			</c:otherwise>
<%-- 			<c:otherwise> --%>
<%-- 				<li>${ param.menu=="joinsuccess" }님 안녕하세요 ^^;</li> --%>
<%-- 				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li> --%>
<%-- 				<li><a href="${pageContext.request.contextPath}/user/${authuser.id}">내블로그</a></li> --%>
<%-- 			</c:otherwise> --%>
		</c:choose>
	</ul>
</div>