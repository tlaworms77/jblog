<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript">
window.addEventListener( "load", function(){
	onsubmit = function(){
		var inputId = document.getElementById( "blog-id" );
		if( inputId.value === "" ) {
			alert( "이메일은 필수 항목입니다." );
			inputId.focus();
			return false;
		}
		//4. 비밀번호
		var inputPassword = document.getElementById( "password" );
		if( inputPassword.value === "" ) {
			alert( "비밀번호는 필수 항목입니다." );
			inputPassword.focus();
			return false;
		}
	}
});
</script>
</head>
<body>
	<div class="center-content">
		<h1 class="logo">JBlog</h1>
		<c:import url="/WEB-INF/views/includes/header.jsp" >
			<c:param name="menu" value="login" />
		</c:import>
		<form class="login-form" method="post" action="${pageContext.request.contextPath }/user/auth">
      		<label>아이디</label> <input type="text" id="blog-id" name="id" placeholder="input id : (4~15)">
      		<label>패스워드</label> <input type="password" id="password" name="password" placeholder="input password : (4~)">
      		<c:if test='${result == "fail" or param.result == "fail" }'>
				<p style="color: red;">
					로그인이 실패 했습니다.
				</p>
				<br />
			</c:if>
      		<input type="submit" value="로그인" style="width: 100%;">
		</form>
	</div>
</body>
</html>
