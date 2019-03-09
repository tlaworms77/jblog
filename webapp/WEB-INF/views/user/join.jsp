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
<script src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript">
window.addEventListener( "load", function(){
	
	document.getElementById( "join-form" ).
	onsubmit = function(){
		
		var alphaDigit= "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"
		
		//1. 이름
		var inputName = document.getElementById( "name" );
		if( inputName.value === "" ) {
			alert( "이름은 필수 항목입니다." );
			inputName.focus();
			return false;
		}

		//2. 아이디
		var inputId = document.getElementById( "blog-id" );
		if( inputId.value === "" ) {
			alert( "아이디는 필수 항목입니다." );
			inputId.focus();
			return false;
		}
		
		if (inputId.value.length < 4 || inputId.value.length > 15){
		    alert("아이디는 4~15자 이내여야 합니다.");
		    inputId.focus();
		    return false;
		}
		
	    if (inputId.value.indexOf(" ") >= 0) {
		    alert("아이디에는 공백이 들어가면 안됩니다.");
		    inputId.focus();
		    return false;
	    }
	    
	    for (i=0; i<inputId.value.length; i++) {
	      if (alphaDigit.indexOf(inputId.value.substring(i, i+1)) == -1) {
		      alert("ID는 영문과 숫자의 조합만 사용할 수 있습니다.");
		      inputId.focus();
		      return false;
	      }
	    }
		
		//3. 아이디 중복 체크 여부
		var imageCheck = document.getElementById( "img-checkId" );
		if( imageCheck.style.display === "none" ) {
			alert( "이메일 중복 체크를 해 주세요." );
			return false;
		}
		
		//4. 비밀번호
		var inputPassword = document.getElementById( "password" );
		if( inputPassword.value === "" ) {
			alert( "비밀번호는 필수 항목입니다." );
			inputPassword.focus();
			return false;
		}
		if (inputPassword.value.length < 4) {
		    alert("비밀번호는 4자리 이상 입력하셔야 합니다.");
		    inputPassword.value="";
		    inputPassword.focus();
		    return false;
		}

		//5. 약관동의
		var checkAgree = document.getElementById( "agree-prov" );
		if( checkAgree.checked === false ) {
			alert( "가입 약관에 동의 하셔야 합니다." );
			checkAgree.focus();
			return false;
		}		

		// valid!
		return true;
	}
	
	document.getElementById( "blog-id" ).
	addEventListener( "change", function(){
		var idCheck = document.getElementById( "img-checkId" );
		var buttonCheck = document.getElementById( "btn-checkId" );
		
		idCheck.style.display = "none";
		buttonCheck.style.display = "";
	} );
	
	document.getElementById( "btn-checkId" ).
	addEventListener( "click", function(){
		var blogId = document.getElementById( "blog-id" ).value;
		if( blogId === "" ) {
			return;
		}
		//ajax 통신
		$.ajax( {
			url : "${pageContext.servletContext.contextPath }/user/api/join/" + blogId,
			type: "get",
			dataType: "json",
			data: "",
			success: function( response ){
				console.log( response );
				if( response.data == true ) {
					alert( "이미 존재하는 이메일 입니다. 다른 이메일을 사용해 주세요." );
					// email 입력 창 비우고 포커싱
					var inputId = document.getElementById( "blog-id" )
					inputId.value = "";
					inputId.focus();
				} else {
					var imageCheck = document.getElementById( "img-checkId" );
					var buttonCheck = document.getElementById( "btn-checkId" );
					
					imageCheck.style.display = "";
					buttonCheck.style.display = "none";
				}
			},
			error: function( jqXHR, status, error ){
				console.error( status + " : " + error );
			}
		} );	
	} );


});	
</script>
</head>
<body>
	<div class="center-content">
		<h1 class="logo">JBlog</h1>
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<form class="join-form" id="join-form" method="post" action="">
			<label class="block-label" for="name">이름</label>
			<input type="text" id="name" name="name" value="" placeholder="이름을 입력해수세요">
			
			<label class="block-label" for="blog-id">아이디</label>
			<input type="text" id="blog-id" name="id" placeholder="4~15자리 입력해주세요"> 
			<input id="btn-checkId" type="button" value="id 중복체크">
			<img id="img-checkId" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">
			
			<label class="block-label" for="password">패스워드</label>
			<input type="password" id="password" name="password" placeholder="4자리 이상 입력해주세요"/>

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form>
	</div>
</body>
</html>
