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
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript">

var removeRow = function(rowNo){
	$.ajax({
		async: true,
		url: "${pageContext.servletContext.contextPath }/category/api/delete/" + rowNo,
		type: "get",
		dataType: "json",
		data: "",
		success: function(response){
		},
		error: function(xhr, status, e){
			console.log(status + " : " + e);
		}
	});
}

var render = function(vo, mode){
	// 현업에 가면 이렇게 안한다. -> js template Libarary 사용
	// ex) ejs, underscore, mustache
	var htmls = $("<tr>").attr("no", vo.no)
						 .append($("<td>").html(vo.no))
						 .append($("<td>").html(vo.name))
						 .append($("<td>").html(vo.postAmount))
						 .append($("<td>").html(vo.description))
						 .append($("<td>").append($("<img>")
						 .attr("src", "${pageContext.request.contextPath}/assets/images/delete.jpg")
						 .css("cursor", "pointer")
						 .on("click", function(e){
							let $parentTr = $(e.target).closest("tr");
							let rowNo = $parentTr.attr("no");
							removeRow(rowNo);
							$parentTr.remove();
						 })));
	
	if(mode){
		$(".admin-cat > tbody:first").append(htmls);
	} else{
		$(".admin-cat > tbody:first").prepend(htmls);
	}			
}



// 최초 리스트
var fetchList = function (){
	$.ajax({
		async: true,
		url: "${pageContext.servletContext.contextPath }/category/api/list",
		type: "get",
		dataType: "json",
		data: "",
		success: function(response){
			// 값을 가져와서 현재 jsp에 배치
			// rendering
			$.each(response.data, function(index, vo){
				render(vo, true);
			});
		
		},
		error: function(xhr, status, e){
			console.log(status + " : " + e);
		}
	});
}

$(function(){
	// category 등록 폼 submit 이벤트
	$("#add-form").submit(function(event){
		// submit의 기본 동작(POST) -> 막아야한다.
		event.preventDefault();
		
		//validate form data
		var name = $("#input-name").val();	
		var description = $("#input-description").val();
		
		if(name == ""){
			messageBox("글 남기기", "카테고리명은 필수입력 항목입니다.", "#input-name");
			$("#input-name").focus();
			return;
		}
		
		if(description == ""){
			messageBox("글 남기기", "설명은 필수입력 항목입니다.", "#input-description");
			$("#input-description").focus();
			return;
		}
		
		// 여기까지 온것이라면 위 validate를 통과한 것
		// ajax 실시
		$.ajax({
			async: true,
			url: "${pageContext.servletContext.contextPath }/category/api/add/" + $("#input-name").val() + "/" + $("#input-description").val() + "/" + $("#userNo").val(),
			type: "get",
			dataType: "json",
			data: "",
			success: function(response){
				// 값을 가져와서 현재 jsp에 배치
				render(response.data, false);
				
			},
			error: function(xhr, status, e){
				console.log(status + " : " + e);
			}
		});
		
	});
	
	//categoryList를 가져옴
	fetchList();
	
	// delete click event logic
	$(".delete-btn").click(function(){
		console.log($(this));
		console.log($(this).closest("tr").attr("no"));
	});
	
	
});


</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog-header.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/blog-admin-menu.jsp" >
					<c:param name="menu" value="category" />
				</c:import>
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>
		      		</tr>
		      		<tbody></tbody>
				</table>
      			
      			<form id="add-form" action="" method="post">
	      			<h4 class="n-c">새로운 카테고리 추가</h4>
	      			<input type="hidden" id="userNo" name="userNo" value="${authuser.no }">
			      	<table id="admin-cat-add">
			      		<tr>
			      			<td class="t">카테고리명</td>
			      			<td><input type="text" id="input-name" name="name"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">설명</td>
			      			<td><input type="text" id="input-description" name="description"></td>
			      		</tr>
			      		<tr>
			      			<td class="s">&nbsp;</td>
			      			<td><input type="submit" value="카테고리 추가"></td>
			      		</tr>
			      	</table>
		      	</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>